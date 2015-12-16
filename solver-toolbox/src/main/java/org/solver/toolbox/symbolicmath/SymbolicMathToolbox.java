package org.solver.toolbox.symbolicmath;

import java.io.IOException;

import org.solver.toolbox.toolbox.Toolbox;
import org.springframework.stereotype.Component;

@Component("symbolicMathToolbox")
public class SymbolicMathToolbox implements Toolbox {
	
	protected final Runtime runtime = Runtime.getRuntime();
	
	public void run() {
		try {
			runtime.exec("matlab -nodesktop -r mupad");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
