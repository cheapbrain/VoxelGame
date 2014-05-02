package net.cheapbrain.voxel.blocks;

public class BlockWood extends Block{

	public BlockWood(){
		super(7, true); //passing id to super constructor
		setUntraversable();
		setElasticity(255);
		setHardness(64);
		setResistance(64);
		setRoughness(512);
	}
}
