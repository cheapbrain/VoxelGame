package net.cheapbrain.voxel.blocks;

public class BlockStone extends Block{

	public BlockStone() {
		super(1, true);
		setElasticity(1);
		setResistance(1028);
		setRoughness(128);
	}
	
	@Override
	public void render(int x, int y, int z, int data, boolean[] neighbours) {
		render(x, y, z, new int[]{0}, neighbours);
	}

}
