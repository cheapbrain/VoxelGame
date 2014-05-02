package net.cheapbrain.voxel.utils;

public class Vector3i{
	public int x, y, z;
	
	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object o) {
		if (o==null) return false;
		if (!(((Vector3i)o) instanceof Vector3i)) return false;
		return (x==((Vector3i)o).x)&&(y==((Vector3i)o).y)&&(z==((Vector3i)o).z);
	}

	@Override
	public int hashCode() {
		int result = 23;
		result = result*131 + x;
		result = result*131 + y;
		result = result*131 + z;
		return result;
	}
	
	@Override
	public String toString() {
		return x+" "+y+" "+z;
	}
}
