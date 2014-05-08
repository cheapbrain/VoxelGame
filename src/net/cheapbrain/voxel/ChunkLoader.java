package net.cheapbrain.voxel;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.cheapbrain.voxel.utils.Vector3i;

public class ChunkLoader implements Runnable{
	private Thread thread;
	public Queue<Vector3i> in = new ConcurrentLinkedQueue<Vector3i>();
	public Queue<Chunk[]> out = new ConcurrentLinkedQueue<Chunk[]>();
	public World world;
	private volatile boolean isRunning = true;;
	
	public ChunkLoader(World world) {
		this.world = world;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		while(isRunning) {
			if (!in.isEmpty()) {
				Vector3i v = in.poll();
				out.add(MapGenerator.generateChunk(world.getSeed(), v.x, v.z, world.getHeight()));
			}
		}
	}
	
	public void stop() {
		isRunning = false;
	}
}
