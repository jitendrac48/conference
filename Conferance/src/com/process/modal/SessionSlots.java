package com.process.modal;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * Pojo to hold the talk, start time and name together.
 */
public class SessionSlots{
	GregorianCalendar startTime;
	int talk;
	String name;

	public SessionSlots(GregorianCalendar startTime, int talk, String name) {
		super();
		this.startTime = startTime;
		this.talk = talk;
		this.name = name;
	}
	@Override
	public String toString() {
		return " " + showTime(startTime) + " ("+talk+")\t:\t" + name + "";
	}
	private String showTime(GregorianCalendar s2) {
		int second = s2.get(Calendar.SECOND);
		int minute = s2.get(Calendar.MINUTE);
		int hour = s2.get(Calendar.HOUR);
		return " "+hour+":"+minute+":"+second;
	}
	
	public GregorianCalendar getStartTime() {
		return startTime;
	}
	
	public int getTalk() {
		return talk;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + talk;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionSlots other = (SessionSlots) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (startTime.get(Calendar.HOUR) != other.startTime.get(Calendar.HOUR) &&
				startTime.get(Calendar.MINUTE) != other.startTime.get(Calendar.MINUTE) 
				)
			return false;
		if (talk != other.talk)
			return false;
		return true;
	}
}