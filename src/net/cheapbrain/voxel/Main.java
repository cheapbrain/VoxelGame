package net.cheapbrain.voxel;

import net.cheapbrain.voxel.rendering.Screen;

public class Main {
	public static void main(String[] args) {
		Screen.getInstance().init("asgag", 800, 600, null);
		Screen.getInstance().start();
		new GameState().start();
	}

}
