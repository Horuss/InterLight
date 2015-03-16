package pl.edu.agh.kis.interlight.fx.model;

import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.LightSourceIesPropertiesPanel;

public class LightSourceIes extends LightSource {
	
	private Ies ies;
	
	public LightSourceIes(Double height) {
		super(height);
	}

	@Override
	public String toString() {
		return "LightSource " + getId() + " [IES]";
	}

	@Override
	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new LightSourceIesPropertiesPanel(this, guiHelper);
	}

	public Ies getIes() {
		return ies;
	}

	public void setIes(Ies ies) {
		this.ies = ies;
	}
	
}
