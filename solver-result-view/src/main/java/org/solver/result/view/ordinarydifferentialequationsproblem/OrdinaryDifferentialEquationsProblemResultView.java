package org.solver.result.view.ordinarydifferentialequationsproblem;

import org.solver.output.ordinarydifferentialequationsproblem.OrdinaryDifferentialEquationsProblemOutput;
import org.solver.result.view.common.matlab.graph.MatlabGraph;
import org.solver.result.view.resultview.ResultView;
import org.springframework.stereotype.Component;

@Component("ordinaryDifferentialEquationsProblemResultView")
public class OrdinaryDifferentialEquationsProblemResultView implements ResultView<OrdinaryDifferentialEquationsProblemOutput> {

	protected MatlabGraph matlabGraph;
	
	public OrdinaryDifferentialEquationsProblemResultView() {
		matlabGraph = MatlabGraph.getInstance();
	}
	
	public void display(OrdinaryDifferentialEquationsProblemOutput output) {
		matlabGraph.plot2D(output.getT(), output.getY());
	}

}
