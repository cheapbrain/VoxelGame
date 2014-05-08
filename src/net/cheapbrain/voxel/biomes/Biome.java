package net.cheapbrain.voxel.biomes;

import java.util.ArrayList;

public class Biome {
	private int id;
	private int surface,subWaterSurface,last;
	private ArrayList<int[]> layers,subWaterLayers; //primo elem ID, secondo spessore
	
	public Biome(int id,int surface,int subWaterSurface, ArrayList<int[]> layers,ArrayList<int[]> subWaterLayers ,int last){
		setId(id);
		setSurface(surface);
		setSubWaterSurface(subWaterSurface);
		setLast(last);
		setLayers(layers);
		setSubWaterLayers(subWaterLayers);
	}
	
	public Biome(int id,int surface,int subWaterSurface, ArrayList<int[]> layers,int last){
		setId(id);
		setSurface(surface);
		setSubWaterSurface(subWaterSurface);
		setLast(last);
		setLayers(layers);
		setSubWaterLayers(layers);
	}
	public Biome(int id,int surface,int subWaterSurface,int last){
		setId(id);
		setSurface(surface);
		setSubWaterSurface(subWaterSurface);
		setLast(last);
	}
	
	public int getLayerNumber(){
		return layers.size();
	}
	
	public int getCumulativeThick(int x){
		int a=0;
		for(int i=0;i<x;i++){
			a+=this.getLayerThick(i);
		}
		return a;
	}
	
	public int getLayerId(int x){
		return layers.get(x)[0];
	}
	public int getLayerThick(int x){
		return layers.get(x)[1];
	}
	
	public int getSubWaterLayerNumber(){
		return subWaterLayers.size();
	}
	
	public int getSubWaterCumulativeThick(int x){
		int a=0;
		for(int i=0;i<x;i++){
			a+=this.getSubWaterLayerThick(i);
		}
		return a;
	}
	
	public int getSubWaterLayerId(int x){
		return subWaterLayers.get(x)[0];
	}
	public int getSubWaterLayerThick(int x){
		return subWaterLayers.get(x)[1];
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getSubWaterSurface() {
		return subWaterSurface;
	}

	public void setSubWaterSurface(int subWaterSurface) {
		this.subWaterSurface = subWaterSurface;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<int[]> getLayers() {
		return layers;
	}
	public void setLayers(ArrayList<int[]> layers) {
		this.layers = layers;
	}
	public ArrayList<int[]> getSubWaterLayers() {
		return subWaterLayers;
	}
	public void setSubWaterLayers(ArrayList<int[]> subWaterLayers) {
		this.subWaterLayers = subWaterLayers;
	}

}
