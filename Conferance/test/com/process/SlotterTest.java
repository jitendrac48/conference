/**
 * 
 */
package com.process;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.process.engine.Slotter;
import com.process.modal.Chunk;
import com.process.modal.ChunkList;


public class SlotterTest {

	Slotter slotter;
	@Before
	public void init(){
		slotter=new Slotter(new ArrayList<Integer>(Arrays.asList(2,4,6)), 10);
	}
	/**
	 * Test method for {@link com.process.engine.Slotter#divideSpace(int, com.process.modal.ChunkList)}.
	 */
	@Test
	public void testDivideSlots() {

		List<ChunkList> actualTalkList=slotter.divideSpace(1, new ChunkList((new Chunk(6, 0))));
		List<ChunkList> expectedTalkList=new ArrayList<ChunkList>();

		ChunkList talklist=new ChunkList();
		talklist.addChunk(6, 0);
		talklist.addChunk(4, 0);
		talklist.addChunk(2, 5);
		expectedTalkList.add(talklist);

		talklist=new ChunkList();
		talklist.addChunk(6,0);
		talklist.addChunk(4,1);
		talklist.addChunk(2,3);
		expectedTalkList.add(talklist);

		talklist=new ChunkList();
		talklist.addChunk(6,0);
		talklist.addChunk(4,2);
		talklist.addChunk(2,1);
		expectedTalkList.add(talklist);

		talklist=new ChunkList();
		talklist.addChunk(6,1);
		talklist.addChunk(4,0);
		talklist.addChunk(2,2);
		expectedTalkList.add(talklist);

		talklist=new ChunkList();
		talklist.addChunk(6,1);
		talklist.addChunk(4,1);
		talklist.addChunk(2,0);
		expectedTalkList.add(talklist);
		Assert.assertEquals(expectedTalkList, actualTalkList);
	}

	/**
	 * Test method for {@link com.process.engine.Slotter#getSlots(int, int, java.util.List)}.
	 */
		@Test
		public void testGetSlots() {
			List<ChunkList> actualTalkSlots = Slotter.getSlots(2, 3, slotter.divideSpace(1, new ChunkList((new Chunk(6, 0)))));
			List<ChunkList> expectedTalkList=new ArrayList<ChunkList>();
			
			
			ChunkList talklist=new ChunkList();
			talklist=new ChunkList();
			talklist.addChunk(6,0);
			talklist.addChunk(4,1);
			talklist.addChunk(2,3);
			expectedTalkList.add(talklist);

			talklist=new ChunkList();
			talklist.addChunk(6,0);
			talklist.addChunk(4,2);
			talklist.addChunk(2,1);
			expectedTalkList.add(talklist);

			talklist=new ChunkList();
			talklist.addChunk(6,1);
			talklist.addChunk(4,0);
			talklist.addChunk(2,2);
			expectedTalkList.add(talklist);

			talklist=new ChunkList();
			talklist.addChunk(6,1);
			talklist.addChunk(4,1);
			talklist.addChunk(2,0);
			expectedTalkList.add(talklist);
			
			Assert.assertEquals(expectedTalkList, actualTalkSlots);
		}

}
