package net.cheapbrain.voxel;

import static org.lwjgl.opengl.GL11.*;
import net.cheapbrain.voxel.blocks.Block;
import net.cheapbrain.voxel.blocks.BlockManager;

public class Chunk {
	public static final Chunk VOIDCHUNK = new Chunk(0, 0, 0);
	public static final short SIZE = 16;
	private short[][][][] blocks;
	private int displayList;
	private int x, y, z;
	private int rx, ry, rz;
	private int biome = 0;
	private boolean hasChanged = true;
	private boolean isVoid;
	private boolean render;
	
	public Chunk(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		rx = x*SIZE;
		ry = y*SIZE;
		rz = z*SIZE;
		isVoid = true;
		render = false;
	}
	
	public Chunk(int x, int y, int z, short[][][][] blocks) {
		this.x = x;
		this.y = y;
		this.z = z;
		rx = x*SIZE;
		ry = y*SIZE;
		rz = z*SIZE;
		this.blocks = blocks;
		isVoid = false;
		render = true;
	}
	public void setAsChanged() {
		hasChanged = true;
	}
	
	private void load(World world) {
		if (displayList!=0)
			glDeleteLists(displayList, 1);
		displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
			glBegin(GL_QUADS);
				for (int x=0;x<SIZE;x++) {
					for (int y=0;y<SIZE;y++) {
						for (int z=0;z<SIZE;z++) {
							if (blocks[x][y][z][0]!=0) {
								boolean[] neighbours = new boolean[6];
								Block block;
								
								block = z==SIZE-1?world.getBlock(x+rx, y+ry, rz+SIZE):BlockManager.get(blocks[x][y][z+1][0]);
								neighbours[0] = block==null?true:block.isOpaque();
								
								block = y==SIZE-1?world.getBlock(x+rx, ry+SIZE, z+rz):BlockManager.get(blocks[x][y+1][z][0]);
								neighbours[1] = block==null?false:block.isOpaque();
								
								block = y==0?world.getBlock(x+rx, ry-1, z+rz):BlockManager.get(blocks[x][y-1][z][0]);
								neighbours[2] = block==null?false:block.isOpaque();
								
								block = x==0?world.getBlock(rx-1, y+ry, z+rz):BlockManager.get(blocks[x-1][y][z][0]);
								neighbours[3] = block==null?true:block.isOpaque();

								block = x==SIZE-1?world.getBlock(rx+SIZE, y+ry, z+rz):BlockManager.get(blocks[x+1][y][z][0]);
								neighbours[4] = block==null?true:block.isOpaque();

								block = z==0?world.getBlock(x+rx, y+ry, rz-1):BlockManager.get(blocks[x][y][z-1][0]);
								neighbours[5] = block==null?true:block.isOpaque();
								
								BlockManager.get(blocks[x][y][z][0]).render(x+rx, y+ry, z+rz, blocks[x][y][z][1], neighbours);
								
							}
						}
					}
				}
			glEnd();
		glEndList();
		hasChanged = false;
	}
	
	public void destroy() {
		if (!isVoid) {
			glDeleteLists(displayList, 1);
			blocks = null;
		}
	}
	
	public void render(World world) {
		if (render) {
			if (hasChanged) {
				load(world);
				
			}
			glCallList(displayList);
		}
	}
	
	public void setBlock(int x, int y, int z, int id, int data) {
		if (!isVoid) {
			blocks[x][y][z][0] = (short) id;
			blocks[x][y][z][1] = (short) data;
			hasChanged = true;
		}
	}
	
	public Block getBlock(int x, int y, int z) {
		if (!isVoid) {
			if (x<0) x = SIZE + x;
			if (y<0) y = SIZE + y;
			if (z<0) z = SIZE + z;
			return BlockManager.get(blocks[x][y][z][0]);
		}
		return BlockManager.get(1);
	}
	
	public short getBlockId(int x, int y, int z) {
		if (!isVoid) {
			if (x<0) x = SIZE + x;
			if (y<0) y = SIZE + y;
			if (z<0) z = SIZE + z;
			return blocks[x][y][z][0];
		}
		return 1;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
}
