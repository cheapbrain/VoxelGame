package net.cheapbrain.voxel.blocks;

public class BlockSand extends Block{

	public BlockSand(){
		super(4, true); //passing id to super constructor
		setDensity(128);
		setElasticity(255);
		setHardness(16);
		setResistance(16);
		setRoughness(64);
	}
}
