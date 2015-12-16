package org.solver.common.gridboundary;

import org.solver.common.boundary.Boundary;
import org.solver.common.point2d.Point2D;

public class GridBoundary {
	
	protected Point2D<Integer> p1;
	protected Point2D<Integer> p2;
	
	public GridBoundary(Boundary boundary, int xMin, int yMin, int zoom) {
		p1 = new Point2D<Integer>((boundary.getP1().getX() - xMin) * zoom, (boundary.getP1().getY() - yMin)*zoom);
		p2 = new Point2D<Integer>((boundary.getP2().getX() - xMin) * zoom, (boundary.getP2().getY() - yMin)*zoom);
	}
	
	public int getXByY(int y)
	{
		int xd = p2.getX() - p1.getX();
		int yd = p2.getY() - p1.getY();
		if (yd == 0)
		{
			return p1.getX();
		}
		return (y - p1.getY()) * xd / yd + p1.getX();
	}
	
	public int getYByX(int x)
	{
		int xd = p2.getX() - p1.getX();
		int yd = p2.getY() - p1.getY();
		if (xd == 0)
		{
			return p1.getY();
		}
		return (x - p1.getX()) * yd / xd+ p1.getY();
	}

	public Point2D<Integer> getP1() {
		return p1;
	}

	public Point2D<Integer> getP2() {
		return p2;
	}

}

