package org.solver.output.ordinarydifferentialequationsproblem;

import org.solver.output.Output;

public class OrdinaryDifferentialEquationsProblemOutput extends Output {
	
	protected double[] t;
	protected double[][] y;
	
	public OrdinaryDifferentialEquationsProblemOutput(final double[] t, final double[][] y) {
		this.t = t;
		this.y = y;
	}
	
	public double[] getT() {
		return t;
	}
	
	public void setT(final double[] t) {
		this.t = t;
	}
	
	public double[][] getY() {
		return y;
	}
	
	public void setY(final double[][] y) {
		this.y = y;
	}

}
