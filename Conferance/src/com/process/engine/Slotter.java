package com.process.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.process.modal.ChunkListCountComparator;
import com.process.modal.Chunk;
import com.process.modal.ChunkList;

/**
 * Class holds the logic to divide total chunk space into all possible combinations of 
 * smaller predefined chunks.
 */
public class Slotter {
	List<Integer> chunkSizes=new ArrayList<Integer>();
	List<ChunkList> bag=new ArrayList<ChunkList>();
	int chunkSpace=0;
	
	/**
	 * 
	 * @param chunkSizes Unique Sorted list of chunks {30,45,60}
	 * @param chunkSpace total time available for session such as 120 minutes
	 */
	public Slotter(List<Integer> chunkSizes, int chunkSpace) {
		super();
		this.chunkSizes = chunkSizes;
		this.chunkSpace = chunkSpace;
	}

	/**
	 * Its a recursive function, 
	 * it prepares the list of all possible combinations of chunk
	 *  which can be scheduled in a particular chunk space. 
	 * @param index next elements index which is used in the calculation
	 * @param chunkList list of chunks {30, 45, 60}
	 * @return combinations of chunk list
	 */
	public List<ChunkList> divideSpace(int index, ChunkList chunkList){
		// when call reached to the last element in list,
		// its a terminating condition for recursion
		// in this case, we calculate the total sum,
		// if sum is equal to the maximum available Time Slot then add this combination to list. 
		ArrayList<Chunk> chunks = chunkList.getChunks();
		if(index == 0){
			for(int i=0; i <= chunkSpace/chunkSizes.get(index); i++){
				int sum=chunkSizes.get(index)*i;
				for(Chunk nc : chunks){
					sum+=nc.getCount()*nc.getTalk_minutes();
					if(sum > chunkSpace)
						break;
				}
				if(sum == chunkSpace){
					ChunkList tempTalklist=new ChunkList();
					for(Chunk tc : chunks){
						tempTalklist.getChunks().add(new Chunk(tc.getTalk_minutes(), tc.getCount()));
					}
					tempTalklist.getChunks().add(new Chunk(chunkSizes.get(index), i));
					bag.add(tempTalklist);
				}
			}//end of for
			index++;
		}//end of if
		
		// If list contains the indexed element, then, increment the count and follow the recursion
		if( chunks.contains(new Chunk( chunkSizes.get(index),0))){
			// If the indexed element is not yet reached to the maximum dividend, 
			// then increment the count and follow the recursion by decrementing index so that recursion enters into the list further
			if(chunkSpace/chunkSizes.get(index) >= chunks.get(chunks.size()-1).getCount()){
				chunks.get(chunks.size()-1).incrementCount();
				divideSpace(--index, chunkList);
			}
			// else, remove the indexed element from the recursion, 
			// and give control to the previous element with incrementing its count in list, for further recursion
			else{
				chunks.remove(chunks.size()-1);
				if(chunks.isEmpty())
					return bag;
				
				if((chunkSpace/chunks.get(chunks.size()-1).getTalk_minutes()) > chunks.get(chunks.size()-1).getCount()){
					chunks.get(chunks.size()-1).incrementCount();
					divideSpace(index, chunkList);
				}
			}
		}
		// If list do not contain the indexed element, then add the element into recursion
		else{
			chunks.add(new Chunk(chunkSizes.get(index), 0));
			divideSpace(--index, chunkList);
		}
		return bag;
	}//end of function
	
	/**
	 * [Efficiency] 
	 * method prune the all possible combinations of addition to the smaller subset depending on the permissible count. 
	 * @param talklen time slot which is to be in question
	 * @param count maximum permissible count
	 * @param talklists list of possible combinations
	 * @return subset of possible combinations which are valid for this particular case
	 */
	public static List<ChunkList> getSlots(int talklen, int count,List<ChunkList> talklists){
		List<ChunkList> talks=new ArrayList<ChunkList>();
		for(ChunkList tl : talklists){
			if(count >= tl.getCount(talklen)){
				talks.add(tl);
			}
		}
		if(talks.isEmpty()){
			talks.addAll(talklists);
		}
		// Sorting is must here !!!
		Collections.sort(talks,new ChunkListCountComparator(talklen));
		return talks;
	}
}//end of class
