package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.World;

public class BlockMud extends Block {

	public BlockMud() {
		super(5, true);
		//?? proprietà da definire per semifluido
	}	
	
	@Override
	public void render(int x, int y, int z, int data, World world) {
		render(x, y, z, new int[]{5}, neighbours(x, y, z, world));
	}
}
