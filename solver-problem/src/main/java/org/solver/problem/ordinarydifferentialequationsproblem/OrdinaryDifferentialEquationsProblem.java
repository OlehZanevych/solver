package org.solver.problem.ordinarydifferentialequationsproblem;

import org.solver.input.ordinarydifferentialequationsproblem.OrdinaryDifferentialEquationsProblemInput;
import org.solver.input.ordinarydifferentialequationsproblem.OrdinaryDifferentialEquationsProblemOptions;
import org.solver.matlab.problem.ode45.ODE45;
import org.solver.output.ordinarydifferentialequationsproblem.OrdinaryDifferentialEquationsProblemOutput;
import org.solver.problem.Problem;
import org.springframework.stereotype.Component;

import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWCellArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;

@Component("ordinaryDifferentialEquationsProblem")
public class OrdinaryDifferentialEquationsProblem implements Problem<OrdinaryDifferentialEquationsProblemInput, OrdinaryDifferentialEquationsProblemOutput> {

	protected ODE45 solver;
	
	public OrdinaryDifferentialEquationsProblem() {
		try {
			solver = new ODE45();
		} catch (MWException e) {
			e.printStackTrace();
		}
	}
	
	public OrdinaryDifferentialEquationsProblemOutput solve(OrdinaryDifferentialEquationsProblemInput input) {
		String[] strFunctions = input.getStrF();
		MWCellArray strF = new MWCellArray(1, strFunctions.length);
		for (int i = 0; i < strFunctions.length; ++i) {
			strF.set(new int[]{1, i + 1}, strFunctions[i]);
		}
		double t0 = input.getT0();
		double te = input.getTe();
		double[] y0 = input.getY0();
		OrdinaryDifferentialEquationsProblemOptions options = input.getOptions();
		if (options == null) {
			options = new OrdinaryDifferentialEquationsProblemOptions();
		}
		if (options.getRelTol() == null) {
			options.setRelTol(1E-6);
		}
		if (options.getAbsTol() == null) {
			Double[] defaultAbsTol = new Double[input.getY0().length];
			for (int i = 0; i < defaultAbsTol.length; ++i) {
				defaultAbsTol[i] = 1E-6;
			}
			options.setAbsTol(defaultAbsTol);
		}
		double relTol = options.getRelTol();
		Double[] absTol = options.getAbsTol();
		OrdinaryDifferentialEquationsProblemOutput output = null; 
		Object[] solverInput = new Object[]{strF, t0, te, y0, relTol, absTol};
		MWNumericArray resultT = null;
		MWNumericArray resultY = null;
		try {
			Object[] result = solver.solve(2, solverInput);
			resultT = (MWNumericArray)result[0];
			resultY = (MWNumericArray)result[1];
			double[] t = resultT.getDoubleData();
			double[][] y = (double[][])resultY.toDoubleArray();
			output = new OrdinaryDifferentialEquationsProblemOutput(t, y);
		} catch (MWException e) {
			e.printStackTrace();
		} finally {
			MWArray.disposeArray(strF);
	        MWArray.disposeArray(resultT);
	        MWArray.disposeArray(resultY);
		}
		return output;
	}

}
