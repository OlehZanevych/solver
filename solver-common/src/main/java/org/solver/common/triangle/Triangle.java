package org.solver.common.triangle;

public class Triangle {
	
	protected int[] points;
	
	public Triangle (int[] points)
	{
		this.points = new int[3];
		this.points[0] = points[0];
		this.points[1] = points[1];
		this.points[2] = points[2];
	}
	
	public int[] getPoints()
	{
		return points.clone();
	}
	
}
