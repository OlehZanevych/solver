package org.solver.problem.innerpolygoninitialboundaryproblem;

import org.solver.calculation.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemApproximation;
import org.solver.common.point3d.Point3D;
import org.solver.domain.partition.polygontriangulation.PolygonTriangulation;
import org.solver.input.innerpolygoninitialboundaryproblem.InnerPolygonInitialBoundaryProblemInput;
import org.solver.output.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemOutput;
import org.solver.output.innerpolygoninitialboundaryproblem.InnerPolygonInitialBoundaryProblemOutput;
import org.solver.output.polygontriangulation.PolygonTriangulationOutput;
import org.solver.problem.Problem;


public class InnerPolygonInitialBoundaryProblem implements Problem<InnerPolygonInitialBoundaryProblemInput, InnerPolygonInitialBoundaryProblemOutput> {
	
	protected InnerPolygonBoundaryProblemApproximation innerPolygonBoundaryProblemApproximation;
	protected PolygonTriangulation<InnerPolygonInitialBoundaryProblemInput, PolygonTriangulationOutput> polygonTriangulation;
	
	public InnerPolygonInitialBoundaryProblemOutput solve(InnerPolygonInitialBoundaryProblemInput input) {
		PolygonTriangulationOutput polygonTriangulationOutput = polygonTriangulation.solve(input);
		int n = (int)Math.abs((input.getTe() - input.getT0())/input.getTimeStep());
		double c = input.getTimeStep();
		double fConstant = c*input.getF();
		double[] f = new double[polygonTriangulationOutput.getPoints().length];
		for (int i = 0; i < f.length; ++i) {
			f[i] = fConstant;
		}
		double a11 = c*input.getA11();
		double a22 = c*input.getA22();
		double d = 1 + c*input.getD();
		double u = input.getU0();
		double[] currentF = new double[f.length];
		for (int i = 0; i < f.length; ++i) {
			currentF[i] = u + fConstant;
		}
		InnerPolygonBoundaryProblemOutput innerPolygonBoundaryProblemOutput = innerPolygonBoundaryProblemApproximation.solve(a11, a22, d, currentF,
				polygonTriangulationOutput.getPoints(), polygonTriangulationOutput.getBoundariesPoints(),
				polygonTriangulationOutput.getTriangles(), input.getBoundaries());
		for (int i = 1; i < n; ++i) {
			Point3D<Double>[] points = innerPolygonBoundaryProblemOutput.getPoints();
			for (int j = 0; j < f.length; ++j) {
				currentF[j] = points[j].getZ() + fConstant;
			}
			innerPolygonBoundaryProblemOutput = innerPolygonBoundaryProblemApproximation.solve(a11, a22, d, f,
					polygonTriangulationOutput.getPoints(), polygonTriangulationOutput.getBoundariesPoints(),
					polygonTriangulationOutput.getTriangles(), input.getBoundaries());
		}
		return new InnerPolygonInitialBoundaryProblemOutput(innerPolygonBoundaryProblemOutput.getPoints(), innerPolygonBoundaryProblemOutput.getTriangles());
	}

	public InnerPolygonBoundaryProblemApproximation getInnerPolygonBoundaryProblemApproximation() {
		return innerPolygonBoundaryProblemApproximation;
	}

	public void setInnerPolygonBoundaryProblemApproximation(final InnerPolygonBoundaryProblemApproximation innerPolygonBoundaryProblemApproximation) {
		this.innerPolygonBoundaryProblemApproximation = innerPolygonBoundaryProblemApproximation;
	}

	public PolygonTriangulation<InnerPolygonInitialBoundaryProblemInput, PolygonTriangulationOutput> getPolygonTriangulation() {
		return polygonTriangulation;
	}

	public void setPolygonTriangulation(final PolygonTriangulation<InnerPolygonInitialBoundaryProblemInput, PolygonTriangulationOutput> polygonTriangulation) {
		this.polygonTriangulation = polygonTriangulation;
	}

}
