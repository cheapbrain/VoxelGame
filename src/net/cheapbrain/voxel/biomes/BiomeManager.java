package net.cheapbrain.voxel.biomes;

public class BiomeManager {
	private static Biome[] biomes = new Biome[20];
	
	public static Biome get(int id) {
		return biomes[id];
	}
	
	public static void add(Biome biome) {
		biomes[biome.getId()] = biome;
	}
	
	public static void init(){
		
		add(new BiomeDesert());
		add(new BiomePlains());
		add(new BiomePole());
		add(new BiomeIcePlains());
	}
	
}
