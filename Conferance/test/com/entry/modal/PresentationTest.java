package com.entry.modal;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.entry.Presentation;
import com.entry.Presentation.TalkInfo;
import com.process.modal.SESSION;
import com.process.modal.SessionSlots;

public class PresentationTest {
	Presentation presentation=new Presentation();
	@Before
	public void init(){
		presentation.addEntry("Lua for the Masses",30);
		presentation.addEntry("Woah",30);
		presentation.addEntry("Sit Down and Write",30);
		presentation.addEntry("Programming in the Boondocks of Seattle",30);
		presentation.addEntry("Ruby vs. Clojure for Back-End Development",30);
		presentation.addEntry("A World Without HackerNews",30);
		presentation.addEntry("User Interface CSS in Rails Apps",30);
		presentation.addEntry("Overdoing it in Python",45);
		presentation.addEntry("Ruby Errors from Mismatched Gem Versions",45);
		presentation.addEntry("Common Ruby Errors",45);
		presentation.addEntry("Accounting-Driven Development",45);
		presentation.addEntry("Pair Programming vs Noise",45);
		presentation.addEntry("Clojure Ate Scala (on my project)",45);
		presentation.addEntry("Writing Fast Tests Against Enterprise Rails",60);
		presentation.addEntry("Rails for Python Developers lightning Communicating Over Distance",60);
		presentation.addEntry("Rails Magic",60);
		presentation.addEntry("Ruby on Rails: Why We Should Move On",60);
		presentation.addEntry("Ruby on Rails Legacy App Maintenance",60);
	}
	// Test can be Non deterministic 
	@Test
	public void testPrepareChart() {
		HashMap<SESSION, List<SessionSlots>> expected_schedule=new HashMap<SESSION, List<SessionSlots>>();
		List<SessionSlots> ss=new ArrayList<SessionSlots>();
		
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 9, 0), 30, "Lua for the Masses"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 9, 30), 30, "Woah"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 10, 0), 30, "Sit Down and Write"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 10, 30), 30, "Programming in the Boondocks of Seattle"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 11, 0), 30, "Ruby vs. Clojure for Back-End Development"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0,11, 30), 30, "A World Without HackerNews"));
		expected_schedule.put(SESSION.Morning1, ss);
		
		ss=new ArrayList<SessionSlots>();
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 9, 0), 45, "Overdoing it in Python"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 9, 45), 45, "Ruby Errors from Mismatched Gem Versions"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 10, 30), 45, "Common Ruby Errors"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 11, 15), 45, "Accounting-Driven Development"));
		expected_schedule.put(SESSION.Morning2, ss);
		
		
		ss=new ArrayList<SessionSlots>();
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 1,0 ), 60, "Writing Fast Tests Against Enterprise Rails"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 2, 0), 60, "Rails for Python Developers lightning Communicating Over Distance"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 3,0), 60, "Rails Magic"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 4,0), 60, "Ruby on Rails: Why We Should Move On"));
		expected_schedule.put(SESSION.Afternoon1, ss);
		
		
		ss=new ArrayList<SessionSlots>();
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 1,0 ), 60, "Ruby on Rails Legacy App Maintenance"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 2, 0), 45, "Pair Programming vs Noise"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 2,45), 45, "Clojure Ate Scala (on my project)"));
		ss.add(new SessionSlots(new GregorianCalendar(0, 0, 0, 3,30), 30, "User Interface CSS in Rails Apps"));
		expected_schedule.put(SESSION.Afternoon2, ss);
		HashMap<SESSION, List<SessionSlots>> actual_schedule=presentation.prepareChart();
		
		Assert.assertEquals(expected_schedule, actual_schedule);
		
		
	}//end of testPrepareChart

	int getTotalPresentationTime(){
		int totaltime=0;
		for(TalkInfo entry:presentation.getEntries()){
			totaltime+=entry.getLength();
		}
		return totaltime;

	}
}
