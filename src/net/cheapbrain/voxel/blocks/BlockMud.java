package net.cheapbrain.voxel.blocks;

public class BlockMud extends Block {

	public BlockMud() {
		super(5, true);
		setDensity(128);
		setElasticity(8);
		setHardness(32);
		setUnminable();
		setRoughness(64);
	}	
}
