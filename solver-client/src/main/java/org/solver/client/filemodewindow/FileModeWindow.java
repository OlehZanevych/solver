package org.solver.client.filemodewindow;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.solver.client.common.comboboxitem.ComboBoxItem;
import org.solver.facade.facade.Facade;
import org.springframework.context.ApplicationContext;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class FileModeWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	protected final ComboBoxItem[] items = new ComboBoxItem[] {
			new ComboBoxItem("Внутрішня крайова задача в полігональній області", "innerPolygonBoundaryProblemFacade"),
			new ComboBoxItem("Задача Коші для системи звичайних диференціальних рівнянь 1-го порядку", "ordinaryDifferentialEquationsProblemFacade")
	};
	
	protected ApplicationContext context;
	
	protected String outputDirectory = "../output/";
	
	protected Facade<?, ?> facade;
	
	protected SpringLayout layout;
	
	protected JLabel problemLabel;
	protected JComboBox<ComboBoxItem> problemComboBox;
	protected JLabel inputFileLabel;
	protected JTextField inputFileTextField;
	protected JButton inputFileButton;
	protected JLabel outputFileLabel;
	protected JTextField outputFileTextField;
	protected JButton outputFileButton;
	protected JButton solveButton;
	
	public FileModeWindow(ApplicationContext context) {
		
		this.context = context;
		
		setTitle("Файловий режим роботи");
		setSize(1000, 200);
		setResizable(false);
		
		layout = new SpringLayout();
		getContentPane().setLayout(layout);
		
		problemLabel = new JLabel("Задача: ");
		layout.putConstraint(SpringLayout.NORTH, problemLabel, 20, SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.WEST, problemLabel, 15, SpringLayout.WEST, getContentPane());
		getContentPane().add(problemLabel);
		
		problemComboBox = new JComboBox<ComboBoxItem>(new DefaultComboBoxModel<ComboBoxItem>(items));
		setToolboxFacade();
		problemComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setToolboxFacade();
			}
		});
		layout.putConstraint(SpringLayout.NORTH, problemComboBox, -3, SpringLayout.NORTH, problemLabel);
		layout.putConstraint(SpringLayout.WEST, problemComboBox, 10, SpringLayout.EAST, problemLabel);
		layout.putConstraint(SpringLayout.EAST, problemComboBox, 890, SpringLayout.WEST, problemComboBox);
		getContentPane().add(problemComboBox);
		
		inputFileLabel = new JLabel("Файл вхідних даних:");
		layout.putConstraint(SpringLayout.NORTH, inputFileLabel, 30, SpringLayout.SOUTH, problemLabel);
		layout.putConstraint(SpringLayout.WEST, inputFileLabel, 0, SpringLayout.WEST, problemLabel);
		getContentPane().add(inputFileLabel);
		
		inputFileTextField = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, inputFileTextField, 0, SpringLayout.NORTH, inputFileLabel);
		layout.putConstraint(SpringLayout.WEST, inputFileTextField, 5, SpringLayout.EAST, inputFileLabel);
		layout.putConstraint(SpringLayout.EAST, inputFileTextField, 700, SpringLayout.WEST, inputFileTextField);
		getContentPane().add(inputFileTextField);
		
		inputFileButton = new JButton("Вибрати");
		inputFileButton.setFocusable(false);
		layout.putConstraint(SpringLayout.NORTH, inputFileButton, -3, SpringLayout.NORTH, inputFileLabel);
		layout.putConstraint(SpringLayout.WEST, inputFileButton, 20, SpringLayout.EAST, inputFileTextField);
		inputFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("../input"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
				fileChooser.setFileFilter(filter);
				if(fileChooser.showOpenDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
					inputFileTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		getContentPane().add(inputFileButton);
		
		outputFileLabel = new JLabel("Файл результатів:");
		layout.putConstraint(SpringLayout.NORTH, outputFileLabel, 30, SpringLayout.SOUTH, inputFileLabel);
		layout.putConstraint(SpringLayout.WEST, outputFileLabel, 0, SpringLayout.WEST, problemLabel);
		getContentPane().add(outputFileLabel);
		
		outputFileTextField = new JTextField(outputDirectory + "result.txt");
		layout.putConstraint(SpringLayout.NORTH, outputFileTextField, 0, SpringLayout.NORTH, outputFileLabel);
		layout.putConstraint(SpringLayout.WEST, outputFileTextField, 0, SpringLayout.WEST, inputFileTextField);
		layout.putConstraint(SpringLayout.EAST, outputFileTextField, 0, SpringLayout.EAST, inputFileTextField);
		getContentPane().add(outputFileTextField);
		
		outputFileButton = new JButton("Вибрати");
		outputFileButton.setFocusable(false);
		layout.putConstraint(SpringLayout.NORTH, outputFileButton, -3, SpringLayout.NORTH, outputFileLabel);
		layout.putConstraint(SpringLayout.WEST, outputFileButton, 20, SpringLayout.EAST, outputFileTextField);
		outputFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(outputDirectory));
				if(fileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
					outputFileTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		getContentPane().add(outputFileButton);
		
		solveButton = new JButton("Розв'язати");
		solveButton.setFocusable(false);
		layout.putConstraint(SpringLayout.NORTH, solveButton, 20, SpringLayout.SOUTH, outputFileButton);
		layout.putConstraint(SpringLayout.EAST, solveButton, 0, SpringLayout.EAST, outputFileButton);
		solveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				facade.solve(inputFileTextField.getText(), outputFileTextField.getText());
			}
		});
		getContentPane().add(solveButton);
	}
	
	protected void setToolboxFacade() {
		facade = (Facade<?, ?>)context.getBean(((ComboBoxItem)problemComboBox.getSelectedItem()).getValue());
	}
	
}
