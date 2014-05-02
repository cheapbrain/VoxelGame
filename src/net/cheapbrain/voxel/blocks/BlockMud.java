package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockMud extends Block {

	public BlockMud() {
		super(5, true);
		setDensity(128);
		setElasticity(8);
		setHardness(32);
		setUnminable();
		setRoughness(64);
	}	
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[]{5}, neighbours(x, y, z, world));
	}
}
