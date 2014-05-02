package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockGrass extends Block{

	public BlockGrass() {
		super(3, true);
	}
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[] {2, 3, 1}, neighbours(x, y, z, world));
	}

}
