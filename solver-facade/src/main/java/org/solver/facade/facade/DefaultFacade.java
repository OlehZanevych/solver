package org.solver.facade.facade;

import java.io.File;

import org.solver.input.Input;
import org.solver.output.Output;
import org.solver.problem.Problem;
import org.solver.result.print.resultprint.ResultPrint;
import org.solver.result.view.resultview.ResultView;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultFacade<INPUT extends Input, OUTPUT extends Output> implements Facade<INPUT, OUTPUT> {
	
	protected Class<INPUT> inputClass;
	protected Problem<INPUT, OUTPUT> problem;
	protected ResultPrint<OUTPUT> resultPrint;
	protected ResultView<OUTPUT> resultView;
	
	public void solve(final String inputFileName, final String outputFileName) {
		INPUT input = getInputDataFromFile(inputFileName);
		OUTPUT output = solve(input);
		printResult(output, outputFileName);
		viewResult(output);
	}
	
	public INPUT getInputDataFromFile(final String inputFileName) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(new File(inputFileName), inputClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public OUTPUT solve(final INPUT input) {
		return problem.solve(input);
	}
	
	public void printResult(final OUTPUT output, final String outputFileName) {
		resultPrint.print(output, outputFileName);
	}
	
	public void viewResult(final OUTPUT output) {
		resultView.display(output);
	}

	public Class<INPUT> getInputClass() {
		return inputClass;
	}

	public void setInputClass(Class<INPUT> inputClass) {
		this.inputClass = inputClass;
	}

	public Problem<INPUT, OUTPUT> getProblem() {
		return problem;
	}

	public void setProblem(final Problem<INPUT, OUTPUT> problem) {
		this.problem = problem;
	}

	public ResultPrint<OUTPUT> getResultPrint() {
		return resultPrint;
	}

	public void setResultPrint(final ResultPrint<OUTPUT> resultPrint) {
		this.resultPrint = resultPrint;
	}

	public ResultView<OUTPUT> getResultView() {
		return resultView;
	}

	public void setResultView(final ResultView<OUTPUT> resultView) {
		this.resultView = resultView;
	}

}
