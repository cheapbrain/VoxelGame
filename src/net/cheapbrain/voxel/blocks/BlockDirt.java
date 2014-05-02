package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockDirt extends Block{

	public BlockDirt() {
		super(2, true);
		setUntraversable();
		setElasticity(64);
		setHardness(32);
		setResistance(32);
		setUnslidable();
	}
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[]{1}, neighbours(x, y, z, world));
	}
}
