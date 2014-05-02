package net.cheapbrain.voxel.utils;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Loader {
	public static Texture loadTexture(String url) {
		try {
			String[] split = url.split("\\.");
			String type = split[split.length-1].toUpperCase();
			return TextureLoader.getTexture(type, ResourceLoader.getResourceAsStream(url));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static TrueTypeFont loadFont(String url, float size) {
		TrueTypeFont ttf = null;
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream(url);
			
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(size);
			ttf = new TrueTypeFont(awtFont, false);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ttf;
	}
}
