package org.solver.domain.partition.polygontriangulation;

import org.solver.input.Input;
import org.solver.output.Output;

public interface PolygonTriangulation<INPUT extends Input, OUTPUT extends Output> {
	
	public OUTPUT solve(INPUT input);
	
}
