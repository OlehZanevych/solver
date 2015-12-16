package org.solver.input.ordinarydifferentialequationsproblem;

import org.solver.input.Input;

public class OrdinaryDifferentialEquationsProblemInput extends Input {
	
	protected String[] strF;
	protected double t0;
	protected double te;
	protected double[] y0;
	protected OrdinaryDifferentialEquationsProblemOptions options;
	
	public String[] getStrF() {
		return strF;
	}
	
	public void setStrF(final String[] strF) {
		this.strF = strF;
	}
	
	public double getT0() {
		return t0;
	}
	
	public void setT0(final double t0) {
		this.t0 = t0;
	}
	
	public double getTe() {
		return te;
	}
	
	public void setTe(final double te) {
		this.te = te;
	}
	
	public double[] getY0() {
		return y0;
	}
	
	public void setY0(final double[] y0) {
		this.y0 = y0;
	}
	
	public OrdinaryDifferentialEquationsProblemOptions getOptions() {
		return options;
	}
	
	public void setOptions(final OrdinaryDifferentialEquationsProblemOptions options) {
		this.options = options;
	}

}
