package net.cheapbrain.voxel.biomes;

import java.util.ArrayList;

public class BiomeIcePlains extends Biome{
		
		public BiomeIcePlains() {
			super(4, 9, 1, list(), subWaterList(), 1);
		}
		
		public static ArrayList<int[]> list(){
			ArrayList<int[]> a=new ArrayList<int[]>();
			a.add(new int[]{9,5});
			a.add(new int[]{2,5});
			return a;
		}
		
		public static ArrayList<int[]> subWaterList(){
			ArrayList<int[]> b=new ArrayList<int[]>();
			b.add(new int[]{1,5});
			b.add(new int[]{2,10});
			return b;
		}

}
