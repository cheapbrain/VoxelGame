package net.cheapbrain.voxel.utils;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

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
	
	public static void main(String args[]) {
		System.out.println("generating...");
		int res = 1024;
		BufferedImage image = new BufferedImage(res, res, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		
		long seed = 1031*23;
		int w = image.getWidth();
		int h = image.getHeight();
		int max = w*h;
		int vy = max-w;
		for (int y=0;y<h;y++) {
			for (int x=0;x<w;x++) {
				int value = 0;
				double s = 60;
				double k = .5;
				int p = 1;
				
				double v1 = Math.sqrt(fractalNoise(x/s/2d, y/s, seed, p, k, FUNCTION_SUM_P_ABS));
				double v2 = Math.sqrt(fractalNoise(x/s/2d, y/s, seed*31, p, k, FUNCTION_SUM_P_ABS));

				if (y>h/2d) {
					if (y<h/2d+10) {
						v1 *= (y-h/2d)*Math.PI/2;
						v2 *= (y-h/2d)*Math.PI/2;
					}
					else {
						v1 = 1;
						v2 = 1;
					}
				}
				
				if (v1<.3&&v2<.3)
					value = 255;
				
				value = value%256;
				value = (value<<8)+value%256;
				value = (value<<8)+value%256;
				pixels[x+vy] = value;
			}
			vy -= w;
		}
		
		try {
			ImageIO.write(image, "PNG", new File("./res/output.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done.");
	}
}
