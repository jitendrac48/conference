package com.process.modal;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.process.engine.Slotter;
/**
 * Enum to represents the available time slots
 *
 */
public enum SESSION {
	Morning1(9){
        @Override
        public List<ChunkList> getPossibleTimeSlots() {
        	Slotter slot_180=new Slotter(digits,180);
            return slot_180.divideSpace(1, new ChunkList((new Chunk(digits.get(digits.size()-1), 0))));
        }
		@Override
		public void setTalkLenghtSlots(List<Integer> digits) {
			this.digits=digits;
		}
    },
	Morning2(9){
        @Override
        public List<ChunkList> getPossibleTimeSlots() {
        	Slotter slot_180=new Slotter(digits,180);
            return slot_180.divideSpace(1, new ChunkList((new Chunk(digits.get(digits.size()-1), 0))));
        }
		@Override
		public void setTalkLenghtSlots(List<Integer> digits) {
			this.digits=digits;
		}
    },
	Afternoon1(1){
        @Override
        public List<ChunkList> getPossibleTimeSlots() {
        	Slotter slot_180=new Slotter(digits,240);
            return slot_180.divideSpace(1, new ChunkList((new Chunk(digits.get(digits.size()-1), 0))));
        }
		@Override
		public void setTalkLenghtSlots(List<Integer> digits) {
			this.digits=digits;
		}
    },
	Afternoon2(1){
        @Override
        public List<ChunkList> getPossibleTimeSlots() {
        	Slotter slot_180=new Slotter(digits,240);
            return slot_180.divideSpace(1, new ChunkList((new Chunk(digits.get(digits.size()-1), 0))));
        }
		@Override
		public void setTalkLenghtSlots(List<Integer> digits) {
			this.digits=digits;
		}
    };
    
    final GregorianCalendar startTime;
    List<Integer> digits=new ArrayList<Integer>();
	/**
	 * it prepares the list of all possible combinations of tiles slots which can be scheduled in a particular time frame. 
	 */
	public abstract List<ChunkList> getPossibleTimeSlots();
	public abstract void setTalkLenghtSlots(List<Integer> digits);
	private SESSION(int startTime) {
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.set(GregorianCalendar.HOUR, startTime);
		calendar.set(GregorianCalendar.MINUTE, 0);
		calendar.set(GregorianCalendar.SECOND, 0);
		calendar.set(GregorianCalendar.YEAR, 0);
		calendar.set(GregorianCalendar.MONTH, 0);
		calendar.set(GregorianCalendar.DAY_OF_MONTH, 0);
		this.startTime = calendar;
	}
	
	public GregorianCalendar getStartTime() {
		return startTime;
	}
}
