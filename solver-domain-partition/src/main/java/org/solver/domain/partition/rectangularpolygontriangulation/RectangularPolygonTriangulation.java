package org.solver.domain.partition.rectangularpolygontriangulation;

import java.util.HashMap;
import java.util.LinkedList;

import org.solver.common.boundary.Boundary;
import org.solver.common.gridboundary.GridBoundary;
import org.solver.common.point2d.Point2D;
import org.solver.common.triangle.Triangle;
import org.solver.domain.partition.polygontriangulation.PolygonTriangulation;
import org.solver.input.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemInput;
import org.solver.input.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemOptions;
import org.solver.output.polygontriangulation.PolygonTriangulationOutput;
import org.springframework.stereotype.Component;

@Component("rectangularPolygonTriangulation")
public class RectangularPolygonTriangulation implements PolygonTriangulation<InnerPolygonBoundaryProblemInput, PolygonTriangulationOutput> {
	
	public PolygonTriangulationOutput solve(InnerPolygonBoundaryProblemInput input) {
		Boundary[] boundaries = input.getBoundaries();
		int zoom = 10;
		InnerPolygonBoundaryProblemOptions options = input.getOptions();
		if (options != null) {
			if (options.getZoom() != null) {
				zoom = options.getZoom();
			}
		}
		int xMin = boundaries[0].getXMin();
		int yMin = boundaries[0].getYMin();
		int xMax = boundaries[0].getXMax();
		int yMax = boundaries[0].getYMax();
		for (int i = 1; i < boundaries.length; ++i) {
			int temp = boundaries[i].getXMin();
			if (xMin > temp) {
				xMin = temp;
			}
			temp = boundaries[i].getYMin();
			if (yMin > temp) {
				yMin = temp;
			}
			temp = boundaries[i].getXMax();
			if (xMax < temp)
			{
				xMax = temp;
			}
			temp = boundaries[i].getYMax();
			if (yMax < temp)
			{
				yMax = temp;
			}
		}
		int n = (yMax - yMin) * zoom;
		int m = (xMax - xMin) * zoom;
		int[][] grid = new int[n + 1][];
		for (int i = 0; i <= n; ++i) {
			grid[i] = new int[m + 1];
			for (int j = 0; j <= m; ++j)
			{
				grid[i][j] = -3;
			}
		}
		int xMinZoomed = xMin * zoom;
		int yMinZoomed = yMin * zoom;
		HashMap<DoubleNode, DoubleNodeBoundaries> doubleNodes = new HashMap<DoubleNode, DoubleNodeBoundaries>();
		for (int i = 0; i < boundaries.length; ++i) {
			GridBoundary gridBoundary = new GridBoundary(boundaries[i], xMin, yMin, zoom);
			int x1 = gridBoundary.getP1().getX();
			int x2 = gridBoundary.getP2().getX();
			if (x1 > x2) {
				int temp = x1;
				x1 = x2;
				x2 = temp;
			}
			if (x1 == x2) {
				int y1 = gridBoundary.getP1().getY();
				int y2 = gridBoundary.getP2().getY();
				if (y1 > y2)
				{
					int temp = y1;
					y1 = y2;
					y2 = temp;
				}
				while (y1 <= y2) {
					int x = gridBoundary.getXByY(y1);
					if (grid[y1][x] == -3) {
						grid[y1][x] = i;
					} else {
						doubleNodes.put(new DoubleNode(y1, x), new DoubleNodeBoundaries(grid[y1][x], i));
						grid[y1][x] = -2;
					}
					++y1;
					
				}
			}
			else {
				while (x1 <= x2) {
					int y = gridBoundary.getYByX(x1);
					if (grid[y][x1]  == -3) {
						grid[y][x1] = i;
					} else {
						doubleNodes.put(new DoubleNode(y, x1), new DoubleNodeBoundaries(grid[y][x1], i));
						grid[y][x1] = -2;
					}
					++x1;
				}
			}
		}
		for (int j = 0; j <= m; ++j) {
			int i = 0;
			while (i <= n) {
				while (i <= n) {
					if (grid[i][j] != -3) {
						int top = i + 1;
						int bottom = i - 1;
						int left = j - 1;
						int right = j + 1;
						if ((top <= n && grid[top][j] != -3) || (left >= 0 && right <= m && ((bottom >= 0 && grid[bottom][left] != -3) || grid[i][left] != -3 || (top <= n && grid[top][left] != -3)) && ((bottom >= 0 && grid[bottom][right] != -3) || grid[i][right] != -3 || (top <= n && grid[top][right] != -3)) )) {
							break;
						}
					}
					++i;
				}
				++i;
				if (i <= n && grid[i][j] != -3) {
					++i;
					while (i <= n && grid[i][j] != -3) {
						++i;
					}
				}
				else {
					while (i <= n) {
						if (grid[i][j] != -3) {
							int top = i + 1;
							int bottom = i - 1;
							int left = j - 1;
							int right = j + 1;
							if ((top <= n && grid[top][j] != -3) || (left >= 0 && right <= m && ((bottom >= 0 && grid[bottom][left] != -3) || grid[i][left] != -3 || (top <= n && grid[top][left] != -3)) && ((bottom >= 0 && grid[bottom][right] != -3) || grid[i][right] != -3 || (top <= n && grid[top][right] != -3)) )) {
								break;
							}
						}
						grid[i][j] = -1;
						++i;
					}
				}
				++i;
			}
		}
		int index = 0;
		LinkedList<Point2D<Double>> tempPointsList = new LinkedList<Point2D<Double>>();
		LinkedList<Integer>[] tempBoundariesPointsList = new LinkedList[boundaries.length];
		for (int i = 0; i < tempBoundariesPointsList.length; ++i) {
			tempBoundariesPointsList[i] = new LinkedList<Integer>();
		}
		for (int j = 0; j <= m; ++j) {
			for (int i = 0; i <= n; ++i) {
				if (grid[i][j] != -3) {
					Point2D<Double> point = new Point2D<Double>((j + xMinZoomed) / (double)zoom,
							(i + yMinZoomed) / (double)zoom);
					tempPointsList.add(point);
					if (grid[i][j] != -1) {
						if (grid[i][j] != -2) {
							tempBoundariesPointsList[grid[i][j]].add(index);
						} else {
							DoubleNodeBoundaries doubleNodeBoundaries = doubleNodes.get(new DoubleNode(i, j));
							tempBoundariesPointsList[doubleNodeBoundaries.getBoundary1()].add(index);
							tempBoundariesPointsList[doubleNodeBoundaries.getBoundary2()].add(index);
						}
					}
					grid[i][j] = index;
					++index;
				}
			}
		}
		Point2D<Double>[] points = tempPointsList.toArray(new Point2D[tempPointsList.size()]);
		LinkedList<Triangle> tempTrianglesList = new LinkedList<Triangle>();
		for (int j = 0; j < m; ++j) {
			for (int i = 0; i < n; ++i) {
				if (grid[i][j] != -3 && grid[i + 1][j + 1] != -3) {
					if (grid[i][j + 1] != -3) {
						tempTrianglesList.add(new Triangle(new int[]
								{
									grid[i][j],
									grid[i][j + 1],
									grid[i + 1][j + 1]
								}));
					}
					if (grid[i + 1][j] != -3) {
						tempTrianglesList.add(new Triangle(new int[]
								{
									grid[i][j],
									grid[i + 1][j + 1],
									grid[i + 1][j]
								}));
					}
				}
				else {
					if (grid[i + 1][j] != -3 && grid[i][j + 1] != -3) {
						if (grid[i][j] != -3) {tempTrianglesList.add(new Triangle(new int[] {
										grid[i][j],
										grid[i][j + 1],
										grid[i + 1][j]
									}));
						}
						if (grid[i + 1][j + 1] != -3) {
							tempTrianglesList.add(new Triangle(new int[]
									{
										grid[i + 1][j],
										grid[i][j + 1],
										grid[i + 1][j + 1]
									}));
						}
					}
				}
			}
		}
		int[][] boundariesPoints = new int[tempBoundariesPointsList.length][];
		for (int i = 0; i < boundariesPoints.length; ++i) {
			boundariesPoints[i] = tempBoundariesPointsList[i].stream().mapToInt(value -> value).toArray();
		}
		Triangle[] triangles = tempTrianglesList.toArray(new Triangle[tempTrianglesList.size()]);
		return new PolygonTriangulationOutput(points, boundariesPoints, triangles);
	}
	
	protected class DoubleNode {
		
		protected int i;
		protected int j;
		
		public DoubleNode(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			DoubleNode other = (DoubleNode) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (i != other.i) {
				return false;
			}
			if (j != other.j) {
				return false;
			}
			return true;
		}

		private RectangularPolygonTriangulation getOuterType() {
			return RectangularPolygonTriangulation.this;
		}
		
	}
	
	public class DoubleNodeBoundaries {
		
		protected int boundary1;
		protected int boundary2;
		
		public DoubleNodeBoundaries(int boundary1, int boundary2) {
			this.boundary1 = boundary1;
			this.boundary2 = boundary2;
		}

		public int getBoundary1() {
			return boundary1;
		}

		public void setBoundary1(int boundary1) {
			this.boundary1 = boundary1;
		}

		public int getBoundary2() {
			return boundary2;
		}

		public void setBoundary2(int boundary2) {
			this.boundary2 = boundary2;
		}
		
	}
	
}
