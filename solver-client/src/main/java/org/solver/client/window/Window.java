package org.solver.client.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

import org.solver.client.filemodewindow.FileModeWindow;
import org.solver.client.toolboxrunwindow.ToolboxRunWindow;
import org.springframework.context.ApplicationContext;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	
	protected ApplicationContext context;
	
	protected SpringLayout layout;
	
	protected JButton fileModeButton;
	protected JButton toolboxRubButton;
	
	public Window(ApplicationContext context) {
		
		setTitle("Вибір режиму роботи");
		setSize(300, 110);
		setResizable(false);
		
		layout = new SpringLayout();
		getContentPane().setLayout(layout);
		
		fileModeButton = new JButton("Файловий режим");
		fileModeButton.setFocusable(false);
		layout.putConstraint(SpringLayout.NORTH, fileModeButton, 20, SpringLayout.NORTH, getContentPane());
		layout.putConstraint(SpringLayout.WEST, fileModeButton, 15, SpringLayout.WEST, getContentPane());
		layout.putConstraint(SpringLayout.EAST, fileModeButton, -15, SpringLayout.EAST, getContentPane());
		fileModeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileModeWindow fileModeWindow = new FileModeWindow(context);
				fileModeWindow.setVisible(true);
			}
		});
		getContentPane().add(fileModeButton);
		
		toolboxRubButton = new JButton("Запуск toolbox");
		toolboxRubButton.setFocusable(false);
		layout.putConstraint(SpringLayout.NORTH, toolboxRubButton, 20, SpringLayout.SOUTH, fileModeButton);
		layout.putConstraint(SpringLayout.WEST, toolboxRubButton, 0, SpringLayout.WEST, fileModeButton);
		layout.putConstraint(SpringLayout.EAST, toolboxRubButton, 0, SpringLayout.EAST, fileModeButton);
		toolboxRubButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToolboxRunWindow toolboxRunWindow = new ToolboxRunWindow(context);
				toolboxRunWindow.setVisible(true);
			}
		});
		getContentPane().add(toolboxRubButton);
		
	}

}
