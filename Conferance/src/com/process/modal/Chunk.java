package com.process.modal;

public class Chunk implements Comparable<Chunk>{
	int talk_minutes;
	int count;
	public Chunk(int number, int count) {
		super();
		this.talk_minutes = number;
		this.count = count;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + talk_minutes;
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
		Chunk other = (Chunk) obj;
		if (talk_minutes != other.talk_minutes)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "(" + talk_minutes + "," + count + ")";
	}
	@Override
	public int compareTo(Chunk o) {
		if(this.count < o.count){
			return 1;
		}
		else{
			return -1;
		}
	}
	public int getTalk_minutes() {
		return talk_minutes;
	}
	public void setTalk_minutes(int talk_minutes) {
		this.talk_minutes = talk_minutes;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void incrementCount(){
		count++;
	}
	public void decrementCount(){
		count--;
	}
}