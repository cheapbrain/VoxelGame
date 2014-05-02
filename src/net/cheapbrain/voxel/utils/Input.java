package net.cheapbrain.voxel.utils;


import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	
	public int mouseDX;
	public int mouseDY;
	
	public int mouseX;
	public int mouseY;
	
	private ArrayList<Button> keys = new ArrayList<Button>();
	
	public Input() {
		keys.add(new Button("mouseL", 1000));
		keys.add(new Button("mouseR", 1001));
		
		keys.add(new Button("up", Keyboard.KEY_UP));
		keys.add(new Button("down", Keyboard.KEY_DOWN));
		keys.add(new Button("left", Keyboard.KEY_LEFT));
		keys.add(new Button("right", Keyboard.KEY_RIGHT));

		keys.add(new Button("up", Keyboard.KEY_W));
		keys.add(new Button("down", Keyboard.KEY_S));
		keys.add(new Button("left", Keyboard.KEY_A));
		keys.add(new Button("right", Keyboard.KEY_D));

		keys.add(new Button("fly", Keyboard.KEY_SPACE));
		keys.add(new Button("fall", Keyboard.KEY_LSHIFT));
		
		keys.add(new Button("esc", Keyboard.KEY_ESCAPE));
		keys.add(new Button("enter", Keyboard.KEY_RETURN));
		
		keys.add(new Button("rotate", Keyboard.KEY_R));
	}
	
	public boolean isKeyPressed(String name){
		boolean state = false;
		int listSize = keys.size();
		for (int i=0;i<listSize;i++)
			if (keys.get(i).name==name) 
				state = keys.get(i).state||state;
		return state;
	}
	
	public void update() {
		
		mouseDX = Mouse.getDX();
		mouseDY = Mouse.getDY();
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		
		while (Mouse.next()) {
			boolean state = Mouse.getEventButtonState();
			switch (Mouse.getEventButton()) {
				case 0: setKeyState(1000, state); break;
				case 1: setKeyState(1001, state); break;
				default : break;
			}
		}
		
		while (Keyboard.next()) {
			boolean state = Keyboard.getEventKeyState();
			int id = Keyboard.getEventKey();
			setKeyState(id, state);			
		}
	}
	
	private void setKeyState(int id, boolean state) {
		int listSize = keys.size();
		for (int i=0;i<listSize;i++) {
			Button button = keys.get(i);
			if (button.id==id) {
				button.state = state;
				keys.set(i, button);
			}
		}
	}
	
	private class Button {
		 
		public String name;
		public int id;
		public boolean state;
		
		public Button(String name, int id) {
			this.name = name;
			this.id = id;
		}		
	}
}
