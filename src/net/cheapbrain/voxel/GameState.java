package net.cheapbrain.voxel;

import static org.lwjgl.opengl.GL11.*;
import net.cheapbrain.voxel.biomes.BiomeManager;
import net.cheapbrain.voxel.blocks.BlockManager;
import net.cheapbrain.voxel.rendering.Camera;
import net.cheapbrain.voxel.rendering.Screen;
import net.cheapbrain.voxel.utils.Input;
import net.cheapbrain.voxel.utils.Timer;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector4f;

public class GameState {
	private World world;
	private Camera camera;
	private Input input;
	private Screen screen;
	
	private Vector4f bg = new Vector4f(0.0f, 0.4f, 0.7f, 0.0f);
	private float fovy = 60.0f;
	private static final int viewDistance = 2;
		
	private void initOpenGL() {
		glShadeModel(GL_FLAT);
		glEnable(GL_TEXTURE_2D);
        glClearColor(bg.x, bg.y, bg.z, bg.w);
        glClearDepth(1.0);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        GLU.gluPerspective(fovy, (float) screen.getWidth()/(float) screen.getHeight(), 0.1f, 1000.0f);
        
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_FASTEST);
        
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
	}
	
	public void start() {
		Mouse.setGrabbed(true);
		screen = Screen.getInstance();
		initOpenGL();
		BlockManager.init();
		BiomeManager.init();
		input = new Input();
		
		int seed = 666;
		System.out.println("seed: "+seed);
		world = new World("sdfa", seed, viewDistance);
		camera = new Camera(0, 150, 0);
		
		loop();
	}
	
	public void loop() {

		Timer tFPS = new Timer(1);
		//Timer timer = new Timer(1d/60);
		boolean running = true;
		int frames = 0;
		while (running) {
			if (Display.isCloseRequested()||input.isKeyPressed("esc"))
				running = false;
			
			update();
			render();
			
			Display.update();
			Display.sync(60);
			frames++;
			
			if (tFPS.isTime()) {
				System.out.println(frames);
				frames = 0;
			}
		}	
		
		world.dispose();
	}
	
	public void update() {
		input.update();
		camera.update(input);
		world.update((int)camera.pos.x, (int) camera.pos.z);
		
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		
		glRotatef(camera.rotation.x, 1, 0, 0);
		glRotatef(camera.rotation.y, 0, 1, 0);
		glTranslatef(-camera.pos.x, -camera.pos.y, +camera.pos.z);
		world.render();
	}
}
