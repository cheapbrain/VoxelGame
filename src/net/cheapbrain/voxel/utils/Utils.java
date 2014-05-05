package net.cheapbrain.voxel.utils;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Utils {
	
	public static FloatBuffer toFB(float[] array) {
		return (FloatBuffer) BufferUtils.createFloatBuffer(array.length).put(array).flip();
	}
	
	public static int sign(int n) {
		return n<0?-1:1;
	}
}
