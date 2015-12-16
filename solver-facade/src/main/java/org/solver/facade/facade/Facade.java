package org.solver.facade.facade;

import org.solver.input.Input;
import org.solver.output.Output;

public interface Facade<INPUT extends Input, OUTPUT extends Output> {
	
	public void solve(final String inputFileName, final String outputFileName);
	
	public INPUT getInputDataFromFile(final String inputFileName);
	
	public OUTPUT solve(final INPUT input);
	
	public void printResult(final OUTPUT result, final String outputFileName);
	
	public void viewResult(final OUTPUT result);

}
