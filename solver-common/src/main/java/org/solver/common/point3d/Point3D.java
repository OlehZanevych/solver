package org.solver.common.point3d;

import org.solver.common.point2d.Point2D;

public class Point3D<T> extends Point2D<T> {
	
	protected T z;
	
	public Point3D() {
		
	}
	
	public Point3D(T x, T y, T z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public T getZ() {
		return z;
	}

	public void setZ(T z) {
		this.z = z;
	}
	
}
