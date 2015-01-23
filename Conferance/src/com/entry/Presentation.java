package com.entry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import com.process.engine.Conference;
import com.process.modal.SESSION;
import com.process.modal.SessionSlots;

public class Presentation {
	public class TalkInfo{
		String subject;
		int length;
		GregorianCalendar startTime;
		public TalkInfo(String subject, int length) {
			super();
			this.subject = subject;
			this.length = length;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public GregorianCalendar getStartTime() {
			return startTime;
		}
		public void setStartTime(GregorianCalendar startTime) {
			this.startTime = startTime;
		}
		private String showTime(GregorianCalendar s2) {
			if(s2 != null){
				int second = s2.get(Calendar.SECOND);
				int minute = s2.get(Calendar.MINUTE);
				int hour = s2.get(Calendar.HOUR);
				return " "+hour+":"+minute+":"+second;
			}
			return "";
		}

		@Override
		public String toString() {
			return "TalkInfo [subject=" + subject + ", length=" + length
					+ ", startTime=" + showTime(startTime) + "]";
		}
	}


	List<TalkInfo> entries;
	HashMap<Integer, ArrayList<TalkInfo>> slots;
	public Presentation() {
		entries=new ArrayList<Presentation.TalkInfo>();
		slots=new HashMap<Integer, ArrayList<TalkInfo>>();
	}

	public void addEntry(String subject, int length){
		TalkInfo t = new TalkInfo(subject, length);
		entries.add(t);

		if(slots.containsKey(length)){
			slots.get(length).add(t);
			slots.put(length, slots.get(length));
		}
		else{
			ArrayList<TalkInfo> talks=new ArrayList<Presentation.TalkInfo>();
			talks.add(t);
			slots.put(length,talks);
		}
	}

	public List<TalkInfo> getEntries() {
		return entries;
	}

	public HashMap<SESSION, List<SessionSlots>> prepareChart() {
		Conference conference=new Conference(slots);
		HashMap<SESSION, List<SessionSlots>> schedule=conference.allocateSeats();	
		
//		for (SESSION session : EnumSet.allOf(SESSION.class)) {
//			List<SessionSlots> sessionSlots=schedule.get(session);
//			System.out.println("---- "+session+ " -----");
//			for(SessionSlots ss:sessionSlots){
//				System.out.println(ss);
//			}
//		}//end of for
//		System.out.println("======================================");
//		for(Entry<Integer, ArrayList<TalkInfo>> e:slots.entrySet()){
//			for(TalkInfo t:e.getValue()){
//				System.out.println(t);
//			}
//		}
		return schedule;
	}//end of method




}
