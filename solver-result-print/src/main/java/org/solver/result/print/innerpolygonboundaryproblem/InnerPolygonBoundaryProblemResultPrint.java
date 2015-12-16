package org.solver.result.print.innerpolygonboundaryproblem;

import java.io.PrintWriter;

import org.solver.common.point3d.Point3D;
import org.solver.common.triangle.Triangle;
import org.solver.output.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemOutput;
import org.solver.result.print.resultprint.ResultPrint;
import org.springframework.stereotype.Component;

@Component("innerPolygonBoundaryProblemResultPrint")
public class InnerPolygonBoundaryProblemResultPrint implements ResultPrint<InnerPolygonBoundaryProblemOutput> {

	public void print(final InnerPolygonBoundaryProblemOutput output, final String outputFileName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outputFileName, "UTF-8");
			writer.println("Значення в точках:");
			String[] titles = new String[] {"№ точки", "x", "y", "z"};
			writer.printf("%10s | %31s | %31s | %31s\r\n", (Object[])titles);
			int i = 0;
			for (Point3D<Double> point3D : output.getPoints()) {
				writer.printf("%10d | %31.6f | %31.6f | %31.6f\r\n", i, point3D.getX(), point3D.getY(), point3D.getZ());
				++i;
			}
			writer.println();
			writer.println("Трикутники (номери точок, які є вершинами):");
			titles = new String[] {"a", "b", "c"};
			writer.printf("%10s | %10s | %10s\r\n", (Object[])titles);
			for (Triangle triangle : output.getTriangles()) {
				int[] points = triangle.getPoints();
				writer.printf("%10d | %10d | %10d\r\n", points[0], points[1], points[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
