package net.cheapbrain.voxel;

import net.cheapbrain.voxel.blocks.Block;
import net.cheapbrain.voxel.rendering.Textures;
import net.cheapbrain.voxel.utils.Utils;

public class World {
	private static final int WORLDH = 16;
	private int viewDistance;
	private int arrayW;
	public Chunk[][][] chunks; // x z y
	@SuppressWarnings("unused")
	private String name;
	private int seed;
	//private int[][] heat;
	
	public World(String name, int seed, int viewDistance) {
		this.viewDistance = viewDistance;
		this.name = name;
		this.seed = seed;
		calculateArraySize();
		chunks = new Chunk[arrayW][arrayW][WORLDH];
		//heat=new int[?][?];
	}
	
	/*public void generateHeatmap(int[][] heat){
		for(float x=0f; x<(float)(?/10f); x=x+0.1f){
			for(float y=0f; y<(float)(?/10f); y=y+0.1f){
				heat[(int) (x*10)][(int) (y*10)]=(int)(60-((x+0.1f)*(y+0.1f))*(???));
			}
	}*/
	
	
	public boolean isYValid(int y) {
		return (y>=0&&y<WORLDH*Chunk.SIZE);
	}
	
	private void calculateArraySize() {
		arrayW = viewDistance*2+3;
	}
	
	public Block getBlock(int x, int y, int z) {
		if (isYValid(y)) {
			Chunk c = chunks
					[getPositionArray(getChunkPosition(x))]
					[getPositionArray(getChunkPosition(z))]
					[getChunkPosition(y)];
			if (c==null) {
				return null;
			}
			return c.getBlock(x%Chunk.SIZE, y%Chunk.SIZE, z%Chunk.SIZE);
		}
		return null;
	}
	
	public Short getBlockId(int x, int y, int z) {
		if (isYValid(y)) {
			Chunk c = chunks
					[getPositionArray(getChunkPosition(x))]
					[getPositionArray(getChunkPosition(z))]
					[getChunkPosition(y)];
			if (c==null)
				return null;
			return c.getBlockId(x%Chunk.SIZE, y%Chunk.SIZE, z%Chunk.SIZE);
		}
		return null;
	}
	
	private void loadChunk(int ax, int az, int cx, int cz) {
		chunks[ax][az] = MapGenerator.generateChunk(seed, cx, cz, WORLDH);
	}
		
	private void removeChunk(int ax, int az) {
		for (int y=0;y<WORLDH;y++) {
			chunks[ax][az][y].destroy();
			chunks[ax][az][y] = null;
		}
		
	}
	
	private int getChunkPosition(int p) {
		return (int) Math.floor((float)p/Chunk.SIZE);
	}
	
	private int getDistanceArray(int a1, int a2) {
		int d = a2-a1;
		if (Math.abs(d)>arrayW/2) {
			return d-arrayW*Utils.sign(d);
		} else 
			return d;
	}
	
	private int getPositionArray(int p) {
		p = p%arrayW;
		if (p<0)
			return arrayW+p;
		else
			return p;
	}
	
	public void update(int px, int pz) {
		int cx = getChunkPosition(px);
		int cz = getChunkPosition(pz);
		int ax = getPositionArray(cx);
		int az = getPositionArray(cz);
		for (int x=0;x<arrayW;x++)
			for (int z=0;z<arrayW;z++) {
				if (chunks[x][z][0]!=null) {
					int dx = Math.abs(chunks[x][z][0].getX()-cx)-1;
					int dy = Math.abs(chunks[x][z][0].getZ()-cz)-1;
					if (dx>viewDistance||dy>viewDistance)
						removeChunk(x, z);
				}
				
				if (chunks[x][z][0]==null) {
					int dx = getDistanceArray(ax, x);
					int dz = getDistanceArray(az, z);
					if (Math.abs(dx)<=viewDistance&&Math.abs(dz)<=viewDistance) {
						loadChunk(x, z, cx+dx, cz+dz);
						for (int sx=-1;sx<=1;sx++)
							for (int sz=-1;sz<=1;sz++)
								if (sx*sz==0) {
									int asx = getPositionArray(sx+x);
									int asz = getPositionArray(sz+z);
									for (int asy=0;asy<WORLDH;asy++)
										if(chunks[asx][asz][asy]!=null)
											chunks[asx][asz][asy].setAsChanged();
								}
					}
				}
				
			}
	}

	public void render() {
		Textures.bind(Block.texture);
		for (int x=0;x<arrayW;x++)
			for (int z=0;z<arrayW;z++)
				for (int y=0;y<WORLDH;y++) {
					Chunk c = chunks[x][z][y];
					if (c!=null)
						c.render(this);
				}
	}
}
