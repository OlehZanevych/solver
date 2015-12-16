package org.solver.problem;

import org.solver.input.Input;
import org.solver.output.Output;

public interface Problem<INPUT extends Input, OUTPUT extends Output> {
	
	public OUTPUT solve(final INPUT input);

}
