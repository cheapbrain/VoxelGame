package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockStone extends Block{

	public BlockStone() {
		super(1, true);
		setUntraversable();
		setElasticity(255);
		setHardness(128);
		setResistance(192);
		setRoughness(512);
	}
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[]{0}, neighbours(x, y, z, world));
	}

}
