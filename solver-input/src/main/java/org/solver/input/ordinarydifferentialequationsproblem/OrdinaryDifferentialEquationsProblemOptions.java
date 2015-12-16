package org.solver.input.ordinarydifferentialequationsproblem;

public class OrdinaryDifferentialEquationsProblemOptions {
	
	protected Double relTol;
	protected Double[] absTol;
	
	public Double getRelTol() {
		return relTol;
	}
	
	public void setRelTol(final Double relTol) {
		this.relTol = relTol;
	}
	
	public Double[] getAbsTol() {
		return absTol;
	}
	
	public void setAbsTol(final Double[] absTol) {
		this.absTol = absTol;
	}
	
}
