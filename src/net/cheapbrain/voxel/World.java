package net.cheapbrain.voxel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.cheapbrain.voxel.blocks.Block;
import net.cheapbrain.voxel.utils.Vector3i;

public class World {
	private static final int WORLDH = 16;
	private Map<Vector3i, Chunk> chunks = new HashMap<Vector3i, Chunk>();
	private Collection<Chunk> cChunks = chunks.values();
	@SuppressWarnings("unused")
	private String name;
	private int seed;
	
	public World(String name, int seed) {
		this.name = name;
		this.seed = seed;
	}
	
	public boolean isInsideWorld(int z) {
		return (z>=0)&&(z<WORLDH*Chunk.SIZE);
	}
	
	public Block getBlock(int x, int y, int z) {
		Chunk c = chunks.get(new Vector3i((int) Math.floor((float)x/Chunk.SIZE), (int) Math.floor((float)y/Chunk.SIZE), (int) Math.floor((float)z/Chunk.SIZE)));
		if (c==null) {
			return null;
		}
		return c.getBlock(x%Chunk.SIZE, y%Chunk.SIZE, z%Chunk.SIZE);
	}
	
	public Short getBlockId(int x, int y, int z) {
		Chunk c = chunks.get(new Vector3i(x/Chunk.SIZE, y/Chunk.SIZE, z/Chunk.SIZE));
		if (c==null)
			return null;
		return c.getBlockId(x%Chunk.SIZE, y%Chunk.SIZE, z%Chunk.SIZE);
	}
	
	public void loadChunk(int x, int z) {
		Chunk[] tchunks = MapGenerator.generateChunk(seed, x, z, WORLDH);
		for (int y=0;y<WORLDH;y++) {

			chunks.put(new Vector3i(x, y, z), tchunks[y]);
		}
	}
		
	public void removeChunk(int x, int y, int z) {
		chunks.remove(new Vector3i(x, y, z));
	}

	public void render() {
		Block.texture.bind();
		Iterator<Chunk> it = cChunks.iterator();
		while (it.hasNext()) { 
			it.next().render(this);
		}
		
	}
}
