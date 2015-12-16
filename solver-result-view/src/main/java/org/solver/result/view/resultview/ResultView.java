package org.solver.result.view.resultview;

import org.solver.output.Output;

public interface ResultView<OUTPUT extends Output> {
	
	public void display(OUTPUT output);

}
