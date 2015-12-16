package org.solver.facade.toolbox;

import org.solver.facade.toolbox.ToolboxFacade;
import org.solver.toolbox.toolbox.Toolbox;

public class DefaultToolboxFacade implements ToolboxFacade {

	protected Toolbox toolbox;
	
	public void run() {
		toolbox.run();
	}

	public Toolbox getToolbox() {
		return toolbox;
	}

	public void setToolbox(final Toolbox toolbox) {
		this.toolbox = toolbox;
	}

}
