package org.solver.client.toolboxrunwindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import org.solver.client.common.comboboxitem.ComboBoxItem;
import org.solver.facade.toolbox.ToolboxFacade;
import org.springframework.context.ApplicationContext;

public class ToolboxRunWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected final ComboBoxItem[] items = new ComboBoxItem[] {
			new ComboBoxItem("Диференціальні рівняння в часткових похідних", "partialDifferentialEquationToolboxFacade"),
			new ComboBoxItem("Символьна математика", "symbolicMathToolboxFacade")
	};
	
	protected ApplicationContext context;
	
	protected ToolboxFacade toolbooxFacade;
	
	protected SpringLayout layout;
	
	protected JLabel toolboxLabel;
	protected JComboBox<ComboBoxItem> toolboxComboBox;
	protected JButton runButton;
	
	public ToolboxRunWindow(ApplicationContext context) {
		
		this.context = context;
		
		setTitle("Вибір toolbox");
		setSize(890, 120);
		setResizable(false);
		
		layout = new SpringLayout();
		getContentPane().setLayout(layout);
		
		toolboxLabel = new JLabel("Toolbox:");
		layout.putConstraint(SpringLayout.NORTH, toolboxLabel, 20, SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.WEST, toolboxLabel, 15, SpringLayout.WEST, getContentPane());
		getContentPane().add(toolboxLabel);
		
		toolboxComboBox = new JComboBox<ComboBoxItem>(new DefaultComboBoxModel<ComboBoxItem>(items));
		setToolboxFacade();
		toolboxComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setToolboxFacade();
			}
		});
		layout.putConstraint(SpringLayout.NORTH, toolboxComboBox, -3, SpringLayout.NORTH, toolboxLabel);
		layout.putConstraint(SpringLayout.WEST, toolboxComboBox, 10, SpringLayout.EAST, toolboxLabel);
		layout.putConstraint(SpringLayout.EAST, toolboxComboBox, 780, SpringLayout.WEST, toolboxComboBox);
		getContentPane().add(toolboxComboBox);
		
		runButton = new JButton("Запустити");
		runButton.setFocusable(false);
		layout.putConstraint(SpringLayout.NORTH, runButton, 20, SpringLayout.SOUTH, toolboxComboBox);
		layout.putConstraint(SpringLayout.EAST, runButton, 0, SpringLayout.EAST, toolboxComboBox);
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toolbooxFacade.run();
			}
		});
		getContentPane().add(runButton);
		
	}
	
	protected void setToolboxFacade() {
		toolbooxFacade = (ToolboxFacade)context.getBean(((ComboBoxItem)toolboxComboBox.getSelectedItem()).getValue());
	}

}
