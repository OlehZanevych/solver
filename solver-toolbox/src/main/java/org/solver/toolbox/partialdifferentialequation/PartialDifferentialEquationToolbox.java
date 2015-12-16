package org.solver.toolbox.partialdifferentialequation;

import java.io.IOException;

import org.solver.toolbox.toolbox.Toolbox;
import org.springframework.stereotype.Component;

@Component("partialDifferentialEquationToolbox")
public class PartialDifferentialEquationToolbox implements Toolbox {
	
	protected final Runtime runtime = Runtime.getRuntime();

	public void run() {
		try {
			runtime.exec("matlab -nodesktop -r pdetool");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
