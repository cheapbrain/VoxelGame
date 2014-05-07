package net.cheapbrain.voxel.biomes;

import java.util.ArrayList;

public class BiomeIcePlains extends Biome{
		
		public BiomeIcePlains() {
			super(4, 9, 9, list(), 1);
		}
		
		public static ArrayList<int[]> list(){
			ArrayList<int[]> a=new ArrayList<int[]>();
			a.add(new int[]{9,5});
			a.add(new int[]{2,5});
			return a;
		}

}
