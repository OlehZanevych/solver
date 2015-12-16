package org.solver.output.innerpolygonboundaryproblem;

import org.solver.common.point3d.Point3D;
import org.solver.common.triangle.Triangle;
import org.solver.output.Output;

public class InnerPolygonBoundaryProblemOutput extends Output {
	
	protected Point3D<Double>[] points;
	protected Triangle[] triangles;
	
	public InnerPolygonBoundaryProblemOutput(Point3D<Double>[] points, Triangle[] triangles) {
		this.points = points;
		this.triangles = triangles;
	}

	public Point3D<Double>[] getPoints() {
		return points;
	}

	public void setPoints(final Point3D<Double>[] points) {
		this.points = points;
	}

	public Triangle[] getTriangles() {
		return triangles;
	}

	public void setTriangles(final Triangle[] triangles) {
		this.triangles = triangles;
	}

}
