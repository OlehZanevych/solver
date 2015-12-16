package org.solver.input.innerpolygonboundaryproblem;

import org.solver.common.boundary.Boundary;
import org.solver.input.Input;

public class InnerPolygonBoundaryProblemInput extends Input {

	protected double a11;
	protected double a22;
	protected double d;
	protected double f;
	protected Boundary[] boundaries;
	protected InnerPolygonBoundaryProblemOptions options;

	public double getA11() {
		return a11;
	}

	public void setA11(final double a11) {
		this.a11 = a11;
	}

	public double getA22() {
		return a22;
	}

	public void setA22(final double a22) {
		this.a22 = a22;
	}

	public double getD() {
		return d;
	}

	public void setD(final double d) {
		this.d = d;
	}

	public double getF() {
		return f;
	}

	public void setF(final double f) {
		this.f = f;
	}

	public Boundary[] getBoundaries() {
		return boundaries;
	}

	public void setBoundaries(final Boundary[] boundaries) {
		this.boundaries = boundaries;
	}

	public InnerPolygonBoundaryProblemOptions getOptions() {
		return options;
	}

	public void setOptions(final InnerPolygonBoundaryProblemOptions options) {
		this.options = options;
	}
	
}
