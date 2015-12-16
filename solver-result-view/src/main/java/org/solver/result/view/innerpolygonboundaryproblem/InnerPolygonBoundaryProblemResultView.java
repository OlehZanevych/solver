package org.solver.result.view.innerpolygonboundaryproblem;

import org.solver.common.point3d.Point3D;
import org.solver.common.triangle.Triangle;
import org.solver.output.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemOutput;
import org.solver.result.view.common.matlab.graph.MatlabGraph;
import org.solver.result.view.resultview.ResultView;
import org.springframework.stereotype.Component;

@Component("innerPolygonBoundaryProblemResultView")
public class InnerPolygonBoundaryProblemResultView implements ResultView<InnerPolygonBoundaryProblemOutput> {
	
	protected MatlabGraph matlabGraph;
	
	public InnerPolygonBoundaryProblemResultView() {
		matlabGraph = MatlabGraph.getInstance();
	}
	
	public void display(InnerPolygonBoundaryProblemOutput output) {
		Triangle[] triangles = output.getTriangles();
		Point3D<Double>[] points = output.getPoints();
		matlabGraph.triangularSurf(triangles, points);
	}

}
