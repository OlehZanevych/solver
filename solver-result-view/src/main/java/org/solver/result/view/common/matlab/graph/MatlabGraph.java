package org.solver.result.view.common.matlab.graph;

import java.util.Arrays;

import org.solver.common.point3d.Point3D;
import org.solver.common.triangle.Triangle;
import org.solver.matlab.graph.Graph;

import com.mathworks.toolbox.javabuilder.MWException;

public class MatlabGraph {
	
	protected static MatlabGraph instance;
	
	protected Graph graph;
	
	protected MatlabGraph() {
		try {
			graph = new Graph();
		} catch (MWException e) {
			e.printStackTrace();
		}
	}
	
	public static MatlabGraph getInstance() {
		if (instance == null) {
			instance = new MatlabGraph();
		}
		return instance;
	}
	
	public void plot2D(double[] t, double[][] y) {
		try {
			graph.plot2D(new Object[]{t, y});
		} catch (MWException e) {
			e.printStackTrace();
		}
	}
	
	public void triangularSurf(Triangle[] triangles, Point3D<Double>[] points) {
		int[][] processedTriangles = Arrays.stream(triangles).map(triangle -> Arrays.stream(triangle.getPoints()).map(point -> point + 1).toArray()).toArray(int[][]::new);
		double[] x = Arrays.stream(points).mapToDouble(point -> point.getX()).toArray();
		double[] y = Arrays.stream(points).mapToDouble(point -> point.getY()).toArray();
		double[] z = Arrays.stream(points).mapToDouble(point -> point.getZ()).toArray();
		try {
			graph.triangularSurf(new Object[]{processedTriangles, x, y, z});
		} catch (MWException e) {
			e.printStackTrace();
		}
	}

}
