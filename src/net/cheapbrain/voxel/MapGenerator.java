package net.cheapbrain.voxel;

import net.cheapbrain.voxel.utils.SimplexNoise;

public class MapGenerator {
	
	public static Chunk[] generateChunk(int seed, int cx, int cz, int wheight) {
		short[][][][][] blocks = new short[wheight][Chunk.SIZE][Chunk.SIZE][Chunk.SIZE][2];
		for (int x=0;x<16;x++)
			for (int z=0;z<16;z++) {
				
				double hvalue = SimplexNoise.noise((x+cx*Chunk.SIZE)/100d, (z+cz*Chunk.SIZE)/100d, (float)seed);
				hvalue +=  SimplexNoise.noise((x+cx*Chunk.SIZE)/50d, (z+cz*Chunk.SIZE)/50d, (float)seed)*0.4;
				hvalue +=  SimplexNoise.noise((x+cx*Chunk.SIZE)/10d, (z+cz*Chunk.SIZE)/10d, (float)seed)*0.05;
				hvalue = Math.pow(hvalue, 3);
				int height;
				if (hvalue>0)
					height = (int)(hvalue*32+128);
				else
					height = (int)(hvalue*32+128);
				for (int cy=0;cy<wheight;cy++)
					for (int y=0;y<16;y++) {
						double hole = SimplexNoise.noise((x+cx*Chunk.SIZE)/100d, (y+cy*Chunk.SIZE)/100d, (z+cz*Chunk.SIZE)/100d, (float)seed);
						if (hole*hole>=.01) {
							if (y+cy*Chunk.SIZE==height)
								blocks[cy][x][y][z][0] = 3;
							else if (y+cy*Chunk.SIZE<height-2)
								blocks[cy][x][y][z][0] = 1;
							else if (y+cy*Chunk.SIZE<height)
								blocks[cy][x][y][z][0] = 2;
						}
					}
			}
		Chunk[] chunks = new Chunk[wheight];
		for (int cy=0;cy<wheight;cy++)
			chunks[cy] = new Chunk(cx, cy, cz, blocks[cy]);

		return chunks;
	}
	
}
