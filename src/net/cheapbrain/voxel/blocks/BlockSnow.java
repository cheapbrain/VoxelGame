package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockSnow extends Block{

	public BlockSnow(){
		super(8, true); //passing id to super constructor
		setElasticity(1);
		setResistance(32);
		setRoughness(32);
	}
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[]{9}, neighbours(x, y, z, world));
	}
}
