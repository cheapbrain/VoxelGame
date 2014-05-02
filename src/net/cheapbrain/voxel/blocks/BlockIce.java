package net.cheapbrain.voxel.blocks;

public class BlockIce extends Block{

	public BlockIce(){
		super(9, true); //passing id to super constructor
		setUntraversable();
		setElasticity(255);
		setHardness(128);
		setResistance(128);
		setRoughness(8);
	}
}
