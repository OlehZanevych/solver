package org.solver.problem.innerpolygonboundaryproblem;

import org.solver.output.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemOutput;
import org.solver.output.polygontriangulation.PolygonTriangulationOutput;
import org.solver.problem.Problem;
import org.solver.input.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemInput;
import org.solver.calculation.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemApproximation;

import org.solver.domain.partition.polygontriangulation.PolygonTriangulation;

public class InnerPolygonBoundaryProblem implements Problem<InnerPolygonBoundaryProblemInput, InnerPolygonBoundaryProblemOutput> {
	
	protected InnerPolygonBoundaryProblemApproximation innerPolygonBoundaryProblemApproximation;
	protected PolygonTriangulation<InnerPolygonBoundaryProblemInput, PolygonTriangulationOutput> polygonTriangulation;
	
	public InnerPolygonBoundaryProblemOutput solve(final InnerPolygonBoundaryProblemInput input) {
		PolygonTriangulationOutput polygonTriangulationOutput = polygonTriangulation.solve(input);
		return innerPolygonBoundaryProblemApproximation.solve(input.getA11(), input.getA22(), input.getD(), input.getF(), polygonTriangulationOutput.getPoints(), polygonTriangulationOutput.getBoundariesPoints(), polygonTriangulationOutput.getTriangles(), input.getBoundaries());
	}

	public InnerPolygonBoundaryProblemApproximation getInnerPolygonBoundaryProblemApproximation() {
		return innerPolygonBoundaryProblemApproximation;
	}

	public void setInnerPolygonBoundaryProblemApproximation(final InnerPolygonBoundaryProblemApproximation innerPolygonBoundaryProblemApproximation) {
		this.innerPolygonBoundaryProblemApproximation = innerPolygonBoundaryProblemApproximation;
	}

	public PolygonTriangulation<InnerPolygonBoundaryProblemInput, PolygonTriangulationOutput> getPolygonTriangulation() {
		return polygonTriangulation;
	}

	public void setPolygonTriangulation(final PolygonTriangulation<InnerPolygonBoundaryProblemInput, PolygonTriangulationOutput> polygonTriangulation) {
		this.polygonTriangulation = polygonTriangulation;
	}

}
