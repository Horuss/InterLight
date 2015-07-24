package pl.edu.agh.kis.interlight.fx.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.LightSourcePropertiesPanel;

public class LightSource {

	private static int nextId = 1;

	private final int id;
	private Integer power;
	private Ies ies;
	private Integer dimming;
	private BooleanProperty selected;
	private LightSourcePropertiesPanel propertiesPanel;

	public LightSource() {
		this.id = getNextId();
		this.selected = new SimpleBooleanProperty(true);
	}

	public int getId() {
		return id;
	}

	public int getNextId() {
		return nextId++;
	}

	@Override
	public String toString() {
		return "LightSource " + getId() + " [IES]";
	}

	public Ies getIes() {
		return ies;
	}

	public void setIes(Ies ies) {
		this.ies = ies;
	}

	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new LightSourcePropertiesPanel(this, guiHelper);
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getDimming() {
		return dimming;
	}

	public void setDimming(Integer dimming) {
		this.dimming = dimming;
	}

	public LightSourcePropertiesPanel getPropertiesPanel() {
		return propertiesPanel;
	}

	public void setPropertiesPanel(LightSourcePropertiesPanel propertiesPanel) {
		this.propertiesPanel = propertiesPanel;
	}

	public BooleanProperty getSelected() {
		return selected;
	}

}
