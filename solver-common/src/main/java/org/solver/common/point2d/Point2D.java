package org.solver.common.point2d;

public class Point2D<T>
{
	protected T x;
	protected T y;
	
	public Point2D() {
		
	}
	
	public Point2D(T x, T y)
	{
		this.x = x;
		this.y = y;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

	public T getY() {
		return y;
	}

	public void setY(T y) {
		this.y = y;
	}
	
}
