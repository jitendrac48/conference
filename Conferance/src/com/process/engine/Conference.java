package com.process.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.entry.Presentation.TalkInfo;
import com.process.modal.ChunkListCountComparator;
import com.process.modal.SESSION;
import com.process.modal.SessionSlots;
import com.process.modal.Chunk;
import com.process.modal.ChunkList;

/**
 *	Class is responsible for preparing the chart of talk with time slots
 */
public class Conference {
	HashMap<SESSION, ChunkList> map=new HashMap<SESSION, ChunkList>();
	ChunkList slot_input=new ChunkList();
	HashMap<Integer, ArrayList<TalkInfo>> slots;
	
	public Conference(HashMap<Integer, ArrayList<TalkInfo>> slots) {
		this.slots=slots;
		ChunkList slot_input=new ChunkList();
		List<Integer> talk_slots=new ArrayList<Integer>();
		for(Entry<Integer, ArrayList<TalkInfo>> e : slots.entrySet()){
			slot_input.addChunk(e.getKey(), e.getValue().size());
			talk_slots.add(e.getKey());
		}
		Collections.sort(talk_slots);
		for (SESSION session : EnumSet.allOf(SESSION.class)) {
			session.setTalkLenghtSlots(talk_slots);
			map.put(session, null);
		}//end of for
		this.slot_input = slot_input;
	}

	/**
	 * Method allocates the start time for possible talk shows. 
	 * @return map of session and list of talks scheduled for that session 
	 */
	public HashMap<SESSION, List<SessionSlots>> allocateSeats(){
		// find the time slots for all possible speeches
		EnumSet<SESSION> enumSet=EnumSet.allOf(SESSION.class);
		for (SESSION session : enumSet) {
			setTimeSlot(session,slot_input);
		}
		// convert the time slots on to the over the clock format like 30 min --> 9:15 to 9:45
		return prepareSchedule();
	}

	/**
	 * private method which allocate the slot with particular track session. such as morning, afternoon etc
	 * @param session enum of { Morning1, Morning2 }
	 * @param remaining_slots unallocated time slots
	 */
	private void setTimeSlot(SESSION session, ChunkList remaining_slots) {
		// Retrieve all the possible slot combination for the particular session
		List<ChunkList> possibleSlots = session.getPossibleTimeSlots();
		
		// Sort remaining slots
		Collections.sort(remaining_slots.getChunks());
		
		// Process each remaining slot one by one
		for(Chunk unmapped_slot : remaining_slots.getChunks()){
			if(unmapped_slot.getCount()>0){
				// Retrieve all the possible slot combination for the particular unmapped slot. 
				// by reassigning it, We are reducing the heuristic approach, 
				// As each new combination is always based on the historical calculations,
				// Its keep on pruning the available slots for the schedule
				possibleSlots=Slotter.getSlots(
						unmapped_slot.getTalk_minutes(), 
						unmapped_slot.getCount(), 
						possibleSlots);
			}
		}
		// Sort the possible slots 
		Collections.sort(possibleSlots,new ChunkListCountComparator(remaining_slots.getChunks().get(0).getTalk_minutes()));
		// finally assign the best possible slot for the session
		map.put(session,possibleSlots.get(0));
		// Calculate the remaining un allocated talks. 
		for(Chunk nc : remaining_slots.getChunks()){
			nc.setCount(nc.getCount()-possibleSlots.get(0).getCount(nc.getTalk_minutes()));
		}//end of for
	}//end of method

	/** 
	 * method assigns the start time slot for each talk. 
	 * @return map of session and list of talks scheduled for that session 
	 */
	HashMap<SESSION, List<SessionSlots>> prepareSchedule(){
		HashMap<SESSION, List<SessionSlots>> schedule=new HashMap<SESSION, List<SessionSlots>>();
		
		// Iterate over all the possible sessions { Track1- Morning, Track2- Morning, Track1- Afternoon, Track2-Afternoon}
		for (SESSION session : EnumSet.allOf(SESSION.class)) {
			List<SessionSlots> sessionSlots=new ArrayList<SessionSlots>();
			schedule.put(session, sessionSlots);
			ArrayList<GregorianCalendar> times=new ArrayList<GregorianCalendar>();
			
			// assign the session start time
			times.add(session.getStartTime());
			ChunkList talks = map.get(session);
			GregorianCalendar time=(GregorianCalendar) session.getStartTime().clone();
			for( Chunk t : talks.getChunks()){ 
				if(t.getCount() > 0){
					for(int i=1;i<=t.getCount(); i++){
						
						// set the talk slot whose start time is not yet set. 
						TalkInfo talkInfo = findUnplannedTalk(t.getTalk_minutes(),slots.get(t.getTalk_minutes()));
						
						// Arithmetic operation for the next talk slot start time. 
						if(talkInfo != null && talkInfo.getStartTime() == null){
							talkInfo.setStartTime((GregorianCalendar)time.clone());
							sessionSlots.add(new SessionSlots((GregorianCalendar)time.clone(), t.getTalk_minutes(),talkInfo.getSubject()));
						}
						time.add(GregorianCalendar.MINUTE, t.getTalk_minutes());
					}//end of for
				}//end of if
			}//end of for
		}//end of for
		return schedule;
	}//end of method
	
	/**
	 * Method is used to find the talk slot whose start time is not yet set
	 * @param length length duration of talk in minutes { 30, 45, 60 }
	 * @param talkInfos list of all talks from which the empty talk to be searched
	 * @return talk whose start time is null
	 */
	TalkInfo findUnplannedTalk(int length, ArrayList<TalkInfo> talkInfos){
		for(TalkInfo talkInfo:talkInfos){
			if(talkInfo.getStartTime() == null)
				return talkInfo;
		}
		return null;
	}
}//end of class
