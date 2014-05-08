package net.cheapbrain.voxel.biomes;

import java.util.ArrayList;

public class BiomeDesert extends Biome{
	
	public BiomeDesert() {
		super(1, 4, 4, list(), subWaterList(), 1);
	}
	
	public static ArrayList<int[]> list(){
		ArrayList<int[]> a=new ArrayList<int[]>();
		a.add(new int[]{4,2});
		a.add(new int[]{2,5});
		return a;
	}
	
	public static ArrayList<int[]> subWaterList(){
		ArrayList<int[]> b=new ArrayList<int[]>();
		b.add(new int[]{2,5});
		return b;
	}
	
}
