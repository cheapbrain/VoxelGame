package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockSand extends Block{

	public BlockSand(){
		super(4, true); //passing id to super constructor
		setElasticity(64);
		setResistance(16);
		setRoughness(256);
	}
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[]{4}, neighbours(x, y, z, world));
	}
}
