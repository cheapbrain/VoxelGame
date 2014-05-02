package net.cheapbrain.voxel;

import static org.lwjgl.opengl.GL11.*;
import net.cheapbrain.voxel.blocks.Block;
import net.cheapbrain.voxel.blocks.BlockManager;

public class Chunk {
	public static final short SIZE = 16;
	private short[][][][] blocks;
	private int displayList;
	private int x, y, z;
	private boolean hasChanged = true;
	
	public Chunk(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		blocks = new short[SIZE][SIZE][SIZE][2];
	}
	
	public Chunk(int x, int y, int z, short[][][][] blocks) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.blocks = blocks;
	}
	public void setAsChanged() {
		hasChanged = true;
	}
	
	public void loadChunk(World world) {
		if (displayList!=0)
			glDeleteLists(displayList, 1);
		displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
			glBegin(GL_QUADS);
				for (int x=0;x<SIZE;x++) {
					for (int y=0;y<SIZE;y++) {
						for (int z=0;z<SIZE;z++) {
							if (blocks[x][y][z][0]!=0)
								BlockManager.get(blocks[x][y][z][0]).render(x+this.x*SIZE, y+this.y*SIZE, z+this.z*SIZE, blocks[x][y][z][1], world);
						}
					}
				}
			glEnd();
		glEndList();
		hasChanged = false;
	}
	
	public void render(World world) {
		if (hasChanged)
			loadChunk(world);
		glCallList(displayList);
	}
	
	public void setBlock(int x, int y, int z, int id, int data) {
		blocks[x][y][z][0] = (short) id;
		blocks[x][y][z][1] = (short) data;
		hasChanged = true;
	}
	
	public Block getBlock(int x, int y, int z) {
		if (x<0) x = SIZE + x;
		if (y<0) y = SIZE + y;
		if (z<0) z = SIZE + z;
		return BlockManager.get(blocks[x][y][z][0]);
	}
	
	public short getBlockId(int x, int y, int z) {
		if (x<0) x = SIZE + x;
		if (y<0) y = SIZE + y;
		if (z<0) z = SIZE + z;
		return blocks[x][y][z][0];
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
