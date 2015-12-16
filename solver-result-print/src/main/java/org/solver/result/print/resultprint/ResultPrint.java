package org.solver.result.print.resultprint;

import org.solver.output.Output;

public interface ResultPrint<OUTPUT extends Output> {
	
	public void print(final OUTPUT output, final String outputFileName);

}
