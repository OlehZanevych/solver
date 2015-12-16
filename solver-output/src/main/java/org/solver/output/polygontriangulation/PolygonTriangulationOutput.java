package org.solver.output.polygontriangulation;

import org.solver.common.point2d.Point2D;
import org.solver.common.triangle.Triangle;
import org.solver.output.Output;

public class PolygonTriangulationOutput extends Output {
	
	protected Point2D<Double>[] points;
	protected int[][] boundariesPoints;
	protected Triangle[] triangles;
	
	public PolygonTriangulationOutput(Point2D<Double>[] points, int[][] boundariesPoint2Ds, Triangle[] triangles) {
		this.points = points;
		this.boundariesPoints = boundariesPoint2Ds;
		this.triangles = triangles;
	}

	public Point2D<Double>[] getPoints() {
		return points.clone();
	}

	public int[][] getBoundariesPoints() {
		return boundariesPoints.clone();
	}

	public Triangle[] getTriangles() {
		return triangles.clone();
	}

}
