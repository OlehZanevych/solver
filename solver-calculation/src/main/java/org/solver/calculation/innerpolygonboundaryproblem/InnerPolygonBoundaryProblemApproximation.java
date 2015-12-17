package org.solver.calculation.innerpolygonboundaryproblem;

import org.solver.common.boundary.Boundary;
import org.solver.common.point2d.Point2D;
import org.solver.common.triangle.Triangle;
import org.solver.output.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemOutput;

public interface InnerPolygonBoundaryProblemApproximation {
	
	public InnerPolygonBoundaryProblemOutput solve(final double a11, final double a22, final double d, final double[] f, final Point2D<Double>[] points, final int[][] boundariesPoints, final Triangle[] triangles, final Boundary[] boundarys);

}
