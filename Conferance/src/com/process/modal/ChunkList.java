package com.process.modal;

import java.util.ArrayList;

public class ChunkList{
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chunks == null) ? 0 : chunks.hashCode());
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
		ChunkList other = (ChunkList) obj;
		if (chunks == null) {
			if (other.chunks != null)
				return false;
		} else if (!chunks.equals(other.chunks))
			return false;
		return true;
	}
	ArrayList<Chunk> chunks=new ArrayList<Chunk>();

	public ChunkList(Chunk talk) {
		super();
		this.chunks.add(talk);
	}

	public ChunkList() {
		super();
	}

	public void addChunk(Integer slot, Integer count){
		getChunks().add(new Chunk(slot, count));
	}
	public int getCount(int number){
		for(Chunk n :chunks){
			if(n.talk_minutes == number)
				return n.count;
		}
		return 0;
	}


	public int getTotalCount(){
		int sum=0;
		for(Chunk n :chunks){
			sum+=n.count;
		}
		return sum;
	}

	public Chunk getMaxCount(){
		Chunk talk=new Chunk(0, 0);
		for(Chunk n :chunks){
			if(n.count > talk.count)
				talk=n;
		}
		return talk;
	}

	public ArrayList<Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(ArrayList<Chunk> chunks) {
		this.chunks = chunks;
	}

	@Override
	public String toString() {
		return "\nTalks [" + chunks + "]";
	}
}