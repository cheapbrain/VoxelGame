package net.cheapbrain.voxel.biomes;

import java.util.ArrayList;

public class BiomePlains extends Biome{
		
		public BiomePlains() {
			super(2, 3, 3, list(), 1);
		}
		
		public static ArrayList<int[]> list(){
			ArrayList<int[]> a=new ArrayList<int[]>();
			a.add(new int[]{2,5});
			return a;
		}

}
