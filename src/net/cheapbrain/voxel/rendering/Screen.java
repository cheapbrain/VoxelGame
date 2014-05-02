package net.cheapbrain.voxel.rendering;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Screen {
	
	private int width;
	private int height;
	private String title;
	private String iconUrl;
	
	private static Screen instance = null;
	
	public static Screen getInstance() {
		if (instance==null)
			instance = new Screen();
		return instance;
	}
	
	private Screen() {
		
	}
	
	public void init(String title, int width, int height, String iconUrl) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.iconUrl = iconUrl;
	}
	
	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void destroy() {
		Display.destroy();
	}

	public void restart() {
		destroy();
		start();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		Display.setTitle(title);
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
}

