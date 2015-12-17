package org.solver.result.view.innerpolygoninitialboundaryproblem;

import org.solver.common.point3d.Point3D;
import org.solver.common.triangle.Triangle;
import org.solver.output.innerpolygoninitialboundaryproblem.InnerPolygonInitialBoundaryProblemOutput;
import org.solver.result.view.common.matlab.graph.MatlabGraph;
import org.solver.result.view.resultview.ResultView;
import org.springframework.stereotype.Component;

@Component("innerPolygonInitialBoundaryProblemResultView")
public class InnerPolygonInitialBoundaryProblemResultView implements ResultView<InnerPolygonInitialBoundaryProblemOutput> {
	
	protected MatlabGraph matlabGraph;
	
	public InnerPolygonInitialBoundaryProblemResultView() {
		matlabGraph = MatlabGraph.getInstance();
	}
	
	public void display(InnerPolygonInitialBoundaryProblemOutput output) {
		Triangle[] triangles = output.getTriangles();
		Point3D<Double>[] points = output.getPoints();
		matlabGraph.triangularSurf(triangles, points);
	}

}
