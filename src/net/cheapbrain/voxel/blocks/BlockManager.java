package net.cheapbrain.voxel.blocks;

import java.util.HashMap;
import java.util.Map;

import net.cheapbrain.voxel.rendering.Textures;

public class BlockManager {
	private static Map<Short, Block> blocks = new HashMap<Short, Block>();
	
	public static Block get(int id) {
		return blocks.get((short)id);
	}
	
	public static void add(Block block) {
		blocks.put(block.getId(), block);
	}
	
	public static void remove(int id) {
		blocks.remove(id);
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
