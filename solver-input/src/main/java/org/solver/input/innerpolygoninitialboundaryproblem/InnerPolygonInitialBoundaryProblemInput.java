package org.solver.input.innerpolygoninitialboundaryproblem;

import org.solver.input.innerpolygonboundaryproblem.InnerPolygonBoundaryProblemInput;

public class InnerPolygonInitialBoundaryProblemInput extends InnerPolygonBoundaryProblemInput {
	
	protected double t0;
	protected double u0;
	protected double te;
	protected Double timeStep;
	
	public double getT0() {
		return t0;
	}
	
	public void setT0(final double t0) {
		this.t0 = t0;
	}
	
	public double getU0() {
		return u0;
	}
	
	public void setU0(final double u0) {
		this.u0 = u0;
	}
	
	public double getTe() {
		return te;
	}
	
	public void setTe(final double te) {
		this.te = te;
	}

	public Double getTimeStep() {
		return timeStep;
	}

	public void setTimeStep(Double timeStep) {
		this.timeStep = timeStep;
	}
	
}
