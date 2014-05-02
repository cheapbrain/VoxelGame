package net.cheapbrain.voxel.blocks;

public class BlockSnow extends Block{

	public BlockSnow(){
		super(8, true); //passing id to super constructor
		setDensity(128);
		setElasticity(255);
		setHardness(8);
		setResistance(32);
		setRoughness(32);
	}
}
