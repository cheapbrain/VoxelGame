package net.cheapbrain.voxel.blocks;

public class BlockDirt extends Block{

	public BlockDirt() {
		super(2, true);
		setElasticity(16);
		setResistance(32);
		setRoughness(256);
	}
	
	@Override
	public void render(int x, int y, int z, int data, boolean[] neighbours) {
		render(x, y, z, new int[]{1}, neighbours);
	}
}
