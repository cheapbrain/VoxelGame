package net.cheapbrain.voxel.blocks;

public class BlockAir extends Block{

	public BlockAir() {
		super(0, false);
		setDensity(0);
		setElasticity(0);
		setUnexplodable();
		setUnminable();
		setUnslidable();
	}
	
}
