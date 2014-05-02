package net.cheapbrain.voxel.rendering;

import net.cheapbrain.voxel.utils.Input;

import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	public Vector3f pos;
	public Vector3f rotation = new Vector3f(0, 0, 0);
	
	float moveSpeed = 0.5f;
	float rotSpeed = 0.5f;
	float maxAngle = 80f;
	
	public Camera(float x, float y, float z) {
		pos = new Vector3f(x, y, z);
	}
	
	public void update(Input input) {
		if (true) {
			rotation.y += input.mouseDX*rotSpeed;
			while (rotation.y<0)
				rotation.y += 360;
			
			while (rotation.y>=360)
				rotation.y -= 360;
			
			rotation.x -= input.mouseDY*rotSpeed;
			if (rotation.x>maxAngle) {
				rotation.x = maxAngle;
			} else if (rotation.x<-maxAngle) {
				rotation.x = -maxAngle;
			}
		}
		
		float radAngle = (float) (Math.PI*rotation.y/180);
				
		float cos = (float) Math.cos(radAngle);
		float sin = (float) Math.sin(radAngle);
		
		if (input.isKeyPressed("fly"))   pos.y += moveSpeed;
		if (input.isKeyPressed("fall"))  pos.y -= moveSpeed;
		
		if (input.isKeyPressed("up"))    {
			pos.z -= moveSpeed*cos;
			pos.x += moveSpeed*sin;
		}
		if (input.isKeyPressed("down"))  {
			pos.z += moveSpeed*cos;
			pos.x -= moveSpeed*sin;
		}
		
		if (input.isKeyPressed("left"))  {
			pos.x -= moveSpeed*cos;
			pos.z -= moveSpeed*sin;
		}
		if (input.isKeyPressed("right")) {
			pos.x += moveSpeed*cos;
			pos.z += moveSpeed*sin;
		}
		
	}
}