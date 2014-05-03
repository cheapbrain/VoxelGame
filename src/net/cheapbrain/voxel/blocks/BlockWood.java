package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockWood extends Block{

	public BlockWood(){
		super(7, true); //passing id to super constructor
		setElasticity(4);
		setResistance(64);
		setRoughness(128);
	}
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[]{7,8,8}, neighbours(x, y, z, world));
	}
}
