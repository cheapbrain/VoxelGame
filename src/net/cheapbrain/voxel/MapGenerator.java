package net.cheapbrain.voxel;

import net.cheapbrain.voxel.utils.SimplexNoise;

public class MapGenerator {
	
	public static Chunk[] generateChunk(int seed, int cx, int cz, int wheight) {
		short[][][][][] blocks = new short[wheight][Chunk.SIZE][Chunk.SIZE][Chunk.SIZE][2];
		seed *= 31;
		for (int x=0;x<16;x++)
			for (int z=0;z<16;z++) {
				/*
				double hvalue = fractalNoise( (x+cx*Chunk.SIZE)/300., (z+cz*Chunk.SIZE)/300., seed, 8, .5, FUNCTION_SUM_P);
				
				if (hvalue>0)
					hvalue = hvalue*hvalue*hvalue;
				else
					hvalue *= .5;
				
				int height = (int)(hvalue*64+128);
				*/
				
				for (int cy=0;cy<wheight;cy++)
					for (int y=0;y<16;y++) {
						double density = fractalNoise( (x+cx*Chunk.SIZE)/100., (y+cy*Chunk.SIZE)/100., (z+cz*Chunk.SIZE)/100., seed, 4, .4, FUNCTION_SUM_P);
						//double hole1 = fractalNoise( (x+cx*Chunk.SIZE)/50., (y+cy*Chunk.SIZE)/50., (z+cz*Chunk.SIZE)/50., seed, 1, .5, FUNCTION_SUM_P_ABS)/Math.sqrt(32/(y+cy*Chunk.SIZE+1d));
						//double hole2 = fractalNoise( (z+cz*Chunk.SIZE)/50., (y+cy*Chunk.SIZE)/50., (x+cx*Chunk.SIZE)/50., seed*23, 1, .5, FUNCTION_SUM_P_ABS)/Math.sqrt(32/(y+cy*Chunk.SIZE+1d));
						if (density+Math.pow((128-y-cy*Chunk.SIZE)/32d, 1)>0) {
							if (y+cy*Chunk.SIZE>128)
								blocks[cy][x][y][z][0] = 2;
							else
								blocks[cy][x][y][z][0] = 1;
							/*if (y+cy*Chunk.SIZE==height)
								if (y+cy*Chunk.SIZE<128)
									blocks[cy][x][y][z][0] = 4;
								else
									blocks[cy][x][y][z][0] = 3;
							else if (y+cy*Chunk.SIZE<height-2)
								blocks[cy][x][y][z][0] = 1;
							else if (y+cy*Chunk.SIZE<height)
								blocks[cy][x][y][z][0] = 2;*/
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
