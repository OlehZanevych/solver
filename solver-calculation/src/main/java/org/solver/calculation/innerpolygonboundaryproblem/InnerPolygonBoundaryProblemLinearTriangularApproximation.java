package org.solver.calculation.innerpolygonboundaryproblem;

import org.solver.common.boundary.Boundary;
import org.solver.common.point2d.Point2D;
import org.solver.common.point3d.Point3D;
import org.solver.common.triangle.Triangle;
import org.solver.output.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemOutput;
import org.solver.slae.solver.universal.UniversalSlaeSolver;

public class InnerPolygonBoundaryProblemLinearTriangularApproximation implements InnerPolygonBoundaryProblemApproximation {

	protected UniversalSlaeSolver universalSlaeSolver;
	
	public InnerPolygonBoundaryProblemOutput solve(final double a11, final double a22, final double d, final double f, final Point2D<Double>[] points, final int[][] boundariesPoints, final Triangle[] triangles, final Boundary[] boundarys) {
		double[][] matrix = new double[points.length][];
		for (int i = 0; i < points.length; ++i) {
			matrix[i] = new double[points.length];
			for (int j = 0; j < points.length; ++j) {
				matrix[i][j] = 0;
			}
		}
		double[] vector = new double[points.length];
		for (int i = 0; i < points.length; ++i) {
			vector[i] = 0;
		}
		for (int i = 0; i < triangles.length; ++i) {
			int[] numberOfPoints = triangles[i].getPoints();
			Point2D<Double>[] trianglePoints = new Point2D[3];
			for (int j = 0; j <= 2; ++j) {
				trianglePoints[j] = points[(numberOfPoints[j])];
			}
			double doubleSquare = 0;
			for (int j = 0; j < 2; ++j) {
				doubleSquare += trianglePoints[j].getX() * trianglePoints[j + 1].getY() -
						trianglePoints[j + 1].getX() * trianglePoints[j].getY();
			}
			doubleSquare += trianglePoints[2].getX() * trianglePoints[0].getY() -
					trianglePoints[0].getX() * trianglePoints[2].getY();
			double factor = 1 / (2 * doubleSquare);
			double[] a = new double[3];
			double[] b = new double[3];
			double[] c = new double[3];
			for (int j = 0; j < 3; ++j) {
				a[j] = trianglePoints[(j + 1) % 3].getX() * trianglePoints[(j + 2) % 3].getY()  - trianglePoints[(j + 2) % 3].getX() * trianglePoints[(j + 1) % 3].getY();
				b[j] = trianglePoints[(j + 1) % 3].getY() - trianglePoints[(j + 2) % 3].getY();
				c[j] = trianglePoints[(j + 2) % 3].getX() - trianglePoints[(j + 1) % 3].getX();
			}
			double[][] keMatrix = new double[3][3];
			for (int j = 0; j < 3; ++j) {
				keMatrix[j] = new double[3];
				keMatrix[j][j] = factor * (a11 * b[j] * b[j] + a22 * c[j] * c[j]);
			}
			for (int j = 1; j < 3; ++j) {
				for (int k = 0; k < j; ++k) {
					keMatrix[k][j] = keMatrix[j][k] = factor * (a11 * b[k]*b[j] + a22 * c[k]*c[j]);
				}
			}
			factor = doubleSquare/24;
			double df = 2 * factor;
			double[][] meMatrix = new double[3][3];
			for (int j = 0; j < 3; ++j) {
				meMatrix[j] = new double[3];
				meMatrix[j][j] = df;
			}
			for (int j = 1; j < 3; ++j) {
				for (int k = 0; k < j; ++k) {
					meMatrix[k][j] = meMatrix[j][k] = factor;
				}
			}
			double[] qe = multiply(meMatrix, f);
			for (int j = 0; j < 3; ++j) {
				for (int k = 0; k < 3; ++k) {
					matrix[numberOfPoints[j]][numberOfPoints[k]] += keMatrix[j][k] + d * meMatrix[k][j];
				}
			}
			for (int j = 0; j < 3; ++j) {
				vector[numberOfPoints[j]] += qe[j];
			}
		}
		for (int i = 0; i < boundariesPoints.length; ++i) {
			Boundary boundary = boundarys[i];
			switch(boundary.getCondition()) {
				case Dirichlet: {
					for (int j = 0; j < boundariesPoints[i].length; ++j) {
						matrix[boundariesPoints[i][j]][boundariesPoints[i][j]] += 100000;
						vector[boundariesPoints[i][j]] += 100000*boundary.getC();
					}
				}		
				break;
				case Neumann: {
					if (boundary.getC() != 0) {
						for (int j = 1; j < boundariesPoints[i].length; ++j) {
							Point2D<Double> point1 = points[boundariesPoints[i][j - 1]];
							Point2D<Double> point2 = points[boundariesPoints[i][j]];
							double x = point2.getX() - point1.getX();
							double y = point2.getY() - point1.getY();
							double boundaryLength = Math.sqrt(x * x + y * y);
							double coefficient = boundary.getC() * boundaryLength / 2;
							vector[boundariesPoints[i][j - 1]] += coefficient;
							vector[boundariesPoints[i][j]] += coefficient;
						}
					}
				}
				break;
				case Robin: {
					for (int j = 1; j < boundariesPoints[i].length; ++j) {
						Point2D<Double> point1 = points[boundariesPoints[i][j - 1]];
						Point2D<Double> point2 = points[boundariesPoints[i][j]];
						double x = point2.getX() - point1.getX();
						double y = point2.getY() - point1.getY();
						double boundaryLength = Math.sqrt(x * x + y * y);
						double coefficient = boundary.getD() / boundary.getB() * boundaryLength / 6;
						matrix[boundariesPoints[i][j - 1]][boundariesPoints[i][j - 1]] += 2 * coefficient;
						matrix[boundariesPoints[i][j - 1]][boundariesPoints[i][j]] += coefficient;
						matrix[boundariesPoints[i][j]][boundariesPoints[i][j - 1]] += coefficient;
						matrix[boundariesPoints[i][j]][boundariesPoints[i][j]] += 2 * coefficient;
						coefficient = boundary.getD() / boundary.getB() * boundary.getC() * boundaryLength / 2;
						vector[boundariesPoints[i][j - 1]] += coefficient;
						vector[boundariesPoints[i][j]] += coefficient;
					}
				}
				break;
			}
		}
		double[] z = universalSlaeSolver.solve(matrix, vector);
		Point3D<Double>[] result = new Point3D[z.length];
		for (int i = 0; i < z.length; ++i) {
			result[i] = new Point3D<Double>(points[i].getX(), points[i].getY(), z[i]);
		}
		return new InnerPolygonBoundaryProblemOutput(result, triangles);
	}
	
	public double[] multiply(double[][] matrix,  double f) {
		double[] result = new double[matrix.length];
		for (int i = 0; i < matrix.length; ++i) {
			result[i] = 0;
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int k = 0; k < matrix[0].length; k++) {
				result[i] += matrix[i][k]*f;
			}
        }
		return result;
	}

	public UniversalSlaeSolver getUniversalSlaeSolver() {
		return universalSlaeSolver;
	}

	public void setUniversalSlaeSolver(final UniversalSlaeSolver universalSlaeSolver) {
		this.universalSlaeSolver = universalSlaeSolver;
	}

}
