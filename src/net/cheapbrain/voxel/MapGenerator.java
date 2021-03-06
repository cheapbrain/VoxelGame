package net.cheapbrain.voxel;

import net.cheapbrain.voxel.biomes.BiomeManager;
import net.cheapbrain.voxel.utils.SimplexNoise;

public class MapGenerator {
	
	public static int biome(){
		return 3;
	}
	
	public static Chunk[] generateChunk(int seed, int cx, int cz, int wheight) {
		short[][][][][] blocks = new short[wheight][Chunk.SIZE][Chunk.SIZE][Chunk.SIZE][2];
		seed *= 31;
		
		for (int x=0;x<16;x++)
			for (int z=0;z<16;z++) {
				
				double hvalue = fractalNoise( (x+cx*Chunk.SIZE)/300., (z+cz*Chunk.SIZE)/300., seed, 8, .5, FUNCTION_SUM_P);
				
				if (hvalue>0)
					hvalue = hvalue*hvalue*hvalue;
				else
					hvalue *= .5;
				
				int height = (int)(hvalue*64+128);
				if (height >=wheight*Chunk.SIZE)
					height = wheight*Chunk.SIZE-1;
				
				for (int cy=0;cy<wheight;cy++)
					for (int y=0;y<Chunk.SIZE;y++) {

						if (y+cy*Chunk.SIZE==height)
							if (y+cy*Chunk.SIZE<128)
								blocks[cy][x][y][z][0] = (short) BiomeManager.get(biome()).getSubWaterSurface();
							else
								blocks[cy][x][y][z][0] = (short) BiomeManager.get(biome()).getSurface();
						
						else{
							if (y+cy*Chunk.SIZE<128){
								for(short i=0; i<BiomeManager.get(biome()).getSubWaterLayerNumber();i++){
									if (y+cy*Chunk.SIZE<height-BiomeManager.get(biome()).getSubWaterCumulativeThick(i))
										blocks[cy][x][y][z][0] = (short) BiomeManager.get(biome()).getSubWaterLayerId(i);
								}
								
								if (y+cy*Chunk.SIZE<height-BiomeManager.get(biome()).
										getCumulativeThick(BiomeManager.get(biome()).getLayerNumber())
										-BiomeManager.get(biome()).getLast()){
									blocks[cy][x][y][z][0] = (short) BiomeManager.get(biome()).getLast();
								}
							}
							else{
								for(short i=0; i<BiomeManager.get(biome()).getLayerNumber();i++){
									if (y+cy*Chunk.SIZE<height-BiomeManager.get(biome()).getCumulativeThick(i))
										blocks[cy][x][y][z][0] = (short) BiomeManager.get(biome()).getLayerId(i);
								}
								
								if (y+cy*Chunk.SIZE<height-BiomeManager.get(biome()).
										getCumulativeThick(BiomeManager.get(biome()).getLayerNumber())
										-BiomeManager.get(biome()).getLast()){
									blocks[cy][x][y][z][0] = (short) BiomeManager.get(biome()).getLast();
								}
							}
						}
					}
			}
		Chunk[] chunks = new Chunk[wheight];
		for (int cy=0;cy<wheight;cy++)
			chunks[cy] = new Chunk(cx, cy, cz, blocks[cy]);

		return chunks;
	}
	
	public static final int FUNCTION_SUM_P = 0;
	public static final int FUNCTION_SUM_P_ABS = 1;
	

	public static double fractalNoise(double x, double y, double z, int depth, double p, int f) {
		double noise = 0;
		double p2 = 1;
		double s = 1;
		switch (f) {
			case FUNCTION_SUM_P:
				for (int i=1;i<=depth;i++) {
					noise += SimplexNoise.noise(x*s, y*s, z*s)*p2;
					p2 *= p;
					s += s;
				}
				break;
			case FUNCTION_SUM_P_ABS:
				for (int i=1;i<=depth;i++) {
					noise += Math.abs(SimplexNoise.noise(x*s, y*s, z*s))*p2;
					p2 *= p;
					s += s;
				}
				break;
		}
		return noise;
	}

	public static double fractalNoise(double x, double y, double z, double w, int depth, double p, int f) {
		double noise = 0;
		double p2 = 1;
		double s = 1;
		switch (f) {
			case FUNCTION_SUM_P:
				for (int i=1;i<=depth;i++) {
					noise += SimplexNoise.noise(x*s, y*s, z*s, w*s)*p2;
					p2 *= p;
					s += s;
				}
				break;
			case FUNCTION_SUM_P_ABS:
				for (int i=1;i<=depth;i++) {
					noise += Math.abs(SimplexNoise.noise(x*s, y*s, z*s, w*s))*p2;
					p2 *= p;
					s += s;
				}
				break;
		}
		return noise;
	}
}
