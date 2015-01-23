package com.entry;



public class Entry {
	public static void main(String[] args) {
		Presentation presentation=new Presentation();
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
		
		presentation.prepareChart();
	}//end of main
}//end of class
