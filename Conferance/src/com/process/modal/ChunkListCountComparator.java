package com.process.modal;

import java.util.Comparator;
/**
 * Comparator which sort the list as per the digit
 */
public class ChunkListCountComparator implements Comparator<ChunkList> {

	int digit;
	
	public ChunkListCountComparator(int digit) {
		super();
		this.digit = digit;
	}

	@Override
	public int compare(ChunkList o1, ChunkList o2) {
		if ( o1.getCount(digit) > o2.getCount(digit))
			return -1;
		else 
			return 1;
	}
}
