package net.cheapbrain.voxel.blocks;

public class BlockRubber extends Block{

	public BlockRubber(){
		super(6, true); //passing id to super constructor
		setUntraversable();
		setElasticity(255);
		setHardness(64);
		setResistance(128);
		setUnslidable();
	}
}
