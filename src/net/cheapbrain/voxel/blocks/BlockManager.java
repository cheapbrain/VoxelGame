package net.cheapbrain.voxel.blocks;

import net.cheapbrain.voxel.rendering.Textures;

public class BlockManager {
	private static Block[] blocks = new Block[100];
	
	public static Block get(int id) {
		return blocks[id];
	}
	
	public static void add(Block block) {
		blocks[block.getId()] = block;
	}
	
	public static void init() {
		Block.texture = Textures.load(Block.textureUrl);
		Block.blockTexh = (float)Block.blockTextRes/Block.texture.getTextureWidth();
		Block.blockTexw = (float)Block.blockTextRes/Block.texture.getTextureHeight();
		Block.blocksPerRow = (int)(1f/Block.blockTexw);
		
		add(new BlockAir());
		add(new BlockStone());
		add(new BlockDirt());
		add(new BlockGrass());
		add(new BlockIce());
		add(new BlockSnow());
		add(new BlockMud());
		add(new BlockRubber());
		add(new BlockWood());
		add(new BlockSand());
	}
}
