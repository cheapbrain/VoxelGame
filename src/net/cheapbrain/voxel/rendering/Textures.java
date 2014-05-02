package net.cheapbrain.voxel.rendering;

import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;
import java.util.Map;

import net.cheapbrain.voxel.utils.Loader;

import org.newdawn.slick.opengl.Texture;

public class Textures {
	private static Map<String, TextCounter> textures = new HashMap<String, TextCounter>();
	private static Texture lastBind;
	
	public static Texture load(String url) {
		TextCounter tc;
		if ((tc = textures.get(url))==null) {
			Texture t = Loader.loadTexture(url);
			textures.put(url, new TextCounter(t));
			t.bind();
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		}
		else
			tc.uses++;
		
		return textures.get(url).texture;
	}
	
	public static Texture get(String url) {
		TextCounter tc;
		if ((tc = textures.get(url))!=null) {
			return tc.texture;
		}
		return null;
	}
	
	public static void bind(Texture texture) {
		if (texture!=lastBind) {
			texture.bind();
			lastBind = texture;
		}
	}
	
	public static void bind(String url) {
		TextCounter tc;
		if ((tc = textures.get(url))!=null) {
			if (tc.texture!=lastBind) {
				tc.texture.bind();
				lastBind = tc.texture;
			}
		}
	}
	
	public static void dispose(String url) {
		TextCounter tc;
		if ((tc = textures.get(url))!=null) {
			tc.uses--;
			if (tc.uses<=0) {
				textures.remove(url);
				tc.texture.release();
			}
			
		}
		
	}
	
	private static class TextCounter {
		public Texture texture;
		public int uses = 1;
		
		public TextCounter(Texture texture) {
			this.texture = texture;
		}
	}
}
