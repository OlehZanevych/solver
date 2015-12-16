package org.solver.result.print.ordinarydifferentialequationsproblem;

import java.io.PrintWriter;

import org.solver.output.ordinarydifferentialequationsproblem.OrdinaryDifferentialEquationsProblemOutput;
import org.solver.result.print.resultprint.ResultPrint;
import org.springframework.stereotype.Component;

@Component("ordinaryDifferentialEquationsProblemResultPrint")
public class OrdinaryDifferentialEquationsProblemResultPrint implements ResultPrint<OrdinaryDifferentialEquationsProblemOutput> {

	public void print(OrdinaryDifferentialEquationsProblemOutput output, String outputFileName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(outputFileName, "UTF-8");
			double[] t = output.getT();
			double[][] y = output.getY();
			int n = y[0].length;
			StringBuffer yTitle = new StringBuffer();
			yTitle.append("[y1");
			for (int i = 2; i <= n; ++i) {
				yTitle.append(", y" + i);
			}
			yTitle.append("]");
			writer.println("Значення розв'язку " + yTitle + " в точках:");
			String[] title = new String[n + 1];
			title[0] = "t";
			StringBuffer titlePattern = new StringBuffer();
			titlePattern.append("%31s");
			for (int i = 1; i <= n; ++i) {
				title[i] = "y" + i;
				titlePattern.append(" | %31s");
			}
			titlePattern.append("\r\n");
			writer.printf(titlePattern.toString(), (Object[])title);
			for (int i = 0; i < t.length; ++i) {
				writer.printf("%31s", t[i]);
				for (int j = 0; j < n; ++j) {
					writer.printf(" | %31.6f", y[i][j]);
				}
				writer.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
