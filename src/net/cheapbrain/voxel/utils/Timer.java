package net.cheapbrain.voxel.utils;

public class Timer {
	private final long SECOND = 1000000000;
	private long oldTime;
	private long deltaNS;
	private long lastDelta;
	
	public Timer(double deltaS) {
		oldTime = System.nanoTime();
		deltaNS = (long) (deltaS*SECOND);
	}
	
	public boolean isTime() {
		long time = System.nanoTime();
		long delta = time-oldTime;
		if (delta>=deltaNS) {
			oldTime = time -(delta%deltaNS);
			lastDelta = delta;
			return true;
		}
		return false;
	}
	
	public double getDelta() {
		return (double)lastDelta/SECOND;
	}
}
