package net.cheapbrain.voxel.blocks;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import net.cheapbrain.voxel.World;

public class Block {
	public static final String textureUrl = "res/terrain.png";
	public static final int blockTextRes = 16;
	public static float blockTexw;
	public static float blockTexh;
	public static int blocksPerRow;
	public static Texture texture = null;
	private boolean isOpaque;
	private float resistance; //resistance to mining
	private float hardness; //resistance to explosions
	private float roughness; //resistance to slide
	private float elasticity; //elasticity to collision
	private float density; //resistance to being passed trough
	private short id;
	
	public Block(int id, boolean isOpaque) {
		this.id = (short)id;
		this.isOpaque = isOpaque;
	}
	
	public short getId() {
		return id;
	}
	
	public boolean isOpaque() {
		return isOpaque;
	}
	
	public final boolean isOpaque(int x, int y, int z, World world) {
		Block block = world.getBlock(x, y, z);
		if (block==null) return true;
		return block.isOpaque();
	}
	
	public boolean[] neighbours(int x, int y, int z, World world) {
		boolean[] neighbours = new boolean[6];
		
		neighbours[0] = isOpaque(x, y, z+1, world);
		neighbours[1] = isOpaque(x, y+1, z, world);
		neighbours[2] = isOpaque(x, y-1, z, world);
		neighbours[3] = isOpaque(x-1, y, z, world);
		neighbours[4] = isOpaque(x+1, y, z, world);
		neighbours[5] = isOpaque(x, y, z-1, world);
		
		return neighbours;
	}
	
	public void render(int x, int y, int z, int data, World world) {
		
	}
	
	protected static final void render(int x, int y, int z, int[] texCoords, boolean[] neighbours) {
		boolean isVisible = false;
		for (int i=0;i<6;i++)
			isVisible |= !neighbours[i];
		if (isVisible) {
		
			Vector2f[] coords = new Vector2f[6];
			coords[0] = new Vector2f(0, 0);
			float inset = 0.00001f;
			for (int i=0;i<6;i++)
				if (i<texCoords.length) {
					coords[i] = new Vector2f((texCoords[i]%blocksPerRow)*blockTexw+inset, (texCoords[i]/blocksPerRow)*blockTexh+inset);
				} else
					coords[i] = coords[0];
			
				float blockTexh = Block.blockTexh-inset*2;
				float blockTexw = Block.blockTexw-inset*2;
				glColor3f(1.f,1.f,1.f);
				//-----------------TOP FACE
				if (!neighbours[1]) {
					//glNormal3f(0,1,0);
					glTexCoord2f(coords[1].x,coords[1].y);
					glVertex3f(0+x,1+y,0-z);
					glTexCoord2f(coords[1].x,coords[1].y+blockTexh);
					glVertex3f(0+x,1+y,1-z);
					glTexCoord2f(coords[1].x+blockTexw,coords[1].y+blockTexh);
					glVertex3f(1+x,1+y,1-z);
					glTexCoord2f(coords[1].x+blockTexw,coords[1].y);
					glVertex3f(1+x,1+y,0-z);
				}
				
				glColor3f(.8f,.8f,.8f);
				//-----------------NORTH FACE
				if (!neighbours[0]) {
					//glNormal3f(0,0,-1);
					glTexCoord2f(coords[0].x,coords[0].y);
					glVertex3f(1+x,1+y,0-z);
					glTexCoord2f(coords[0].x,coords[0].y+blockTexh);
					glVertex3f(1+x,0+y,0-z);
					glTexCoord2f(coords[0].x+blockTexw,coords[0].y+blockTexh);
					glVertex3f(0+x,0+y,0-z);
					glTexCoord2f(coords[0].x+blockTexw,coords[0].y);
					glVertex3f(0+x,1+y,0-z);
				}
				//-----------------SOUTH FACE
				if (!neighbours[5]) {
					//glNormal3f(0,0,1);
					glTexCoord2f(coords[5].x,coords[5].y);
					glVertex3f(0+x,1+y,1-z);
					glTexCoord2f(coords[5].x,coords[5].y+blockTexh);
					glVertex3f(0+x,0+y,1-z);
					glTexCoord2f(coords[5].x+blockTexw,coords[5].y+blockTexh);
					glVertex3f(1+x,0+y,1-z);
					glTexCoord2f(coords[5].x+blockTexw,coords[5].y);
					glVertex3f(1+x,1+y,1-z);
				}
	
				glColor3f(.6f,.6f,.6f);
				//-----------------WEST FACE
				if (!neighbours[3]) {
					//glNormal3f(-1,0,0);
					glTexCoord2f(coords[3].x,coords[3].y);
					glVertex3f(0+x,1+y,0-z);
					glTexCoord2f(coords[3].x,coords[3].y+blockTexh);
					glVertex3f(0+x,0+y,0-z);
					glTexCoord2f(coords[3].x+blockTexw,coords[3].y+blockTexh);
					glVertex3f(0+x,0+y,1-z);
					glTexCoord2f(coords[3].x+blockTexw,coords[3].y);
					glVertex3f(0+x,1+y,1-z);
				}
				//-----------------EAST FACE
				if (!neighbours[4]) {
					//glNormal3f(1,0,0);
					glTexCoord2f(coords[4].x,coords[4].y);
					glVertex3f(1+x,1+y,1-z);
					glTexCoord2f(coords[4].x,coords[4].y+blockTexh);
					glVertex3f(1+x,0+y,1-z);
					glTexCoord2f(coords[4].x+blockTexw,coords[4].y+blockTexh);
					glVertex3f(1+x,0+y,0-z);
					glTexCoord2f(coords[4].x+blockTexw,coords[4].y);
					glVertex3f(1+x,1+y,0-z);
				}
	
				glColor3f(.4f,.4f,.4f);
				//-----------------BOTTOM FACE
				if (!neighbours[2]) {
					//glNormal3f(0,-1,0);
					glTexCoord2f(coords[2].x,coords[2].y);
					glVertex3f(1+x,0+y,1-z);
					glTexCoord2f(coords[2].x,coords[2].y+blockTexh);
					glVertex3f(0+x,0+y,1-z);
					glTexCoord2f(coords[2].x+blockTexw,coords[2].y+blockTexh);
					glVertex3f(0+x,0+y,0-z);
					glTexCoord2f(coords[2].x+blockTexw,coords[2].y);
					glVertex3f(1+x,0+y,0-z);
				}
				
		}
	}
	
	public boolean isAir(){ 
		return(id==0);
	}
	
	public boolean canBeMined(){
		return(!isUnminable());
	}
	
	public boolean canBeExploded(){
		return(!isUnexplodable());
	}
	
	public boolean canBeTraversed(){
		return(!isUntraversable());
	}
	
	public boolean canBeSlided(){
		return(!isUnslidable());
	}
	
	public void mined(float power){
		if(canBeMined()){
			resistance = resistance - (power/100);
		}
		
		if(resistance<=0) destroy();
	}
	
	public void isExploded(float power){
		if(canBeExploded()){
			destroy();
		}
	}
	
	public void setUnminable(){
		setResistance(1000f);
	}
	
	public boolean isUnminable(){
		return (resistance>=1000);
	}
	
	public void setUnexplodable(){
		setHardness(1000f);
	}
	
	public boolean isUnexplodable(){
		return (hardness>=1000);
	}
	
	public void setUntraversable(){
		setDensity(1000f);
	}
	
	public boolean isUntraversable(){
		return (density>=1000);
	}
	
	public void setUnslidable(){
		setRoughness(1000f);
	}
	
	public boolean isUnslidable(){
		return (roughness>=1000);
	}
	
	public void destroy(){ //set to Air
		id=0;
		density=0;
		elasticity=0;
		hardness=0;
	}
	
	public float getResistance() {
		return resistance;
	}

	public void setResistance(float resistance) {
		this.resistance = resistance;
	}

	public float getHardness() {
		return hardness;
	}

	public void setHardness(float hardness) {
		this.hardness = hardness;
	}

	public float getElasticity() {
		return elasticity;
	}

	public void setElasticity(float elasticity) {
		this.elasticity = elasticity;
	}

	public float getDensity() {
		return density;
	}

	public void setDensity(float density) {
		this.density = density;
	}

	public float getRoughness() {
		return roughness;
	}

	public void setRoughness(float roughness) {
		this.roughness = roughness;
	}
}
