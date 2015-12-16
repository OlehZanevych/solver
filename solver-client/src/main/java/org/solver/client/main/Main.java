package org.solver.client.main;

import org.solver.client.window.Window;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(final String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		Window window = new Window(context);
		window.setVisible(true);
		
	}

}
