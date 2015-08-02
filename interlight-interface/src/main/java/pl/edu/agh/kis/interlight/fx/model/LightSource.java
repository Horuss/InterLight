package pl.edu.agh.kis.interlight.fx.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.LightSourcePropertiesPanel;
import pl.edu.agh.kis.interlight.ies.IesProfile;

public class LightSource {

	private static int nextId = 1;

	private final int id;
	private IesProfile ies;
	private BooleanProperty selected;
	private LightSourcePropertiesPanel propertiesPanel;

	public LightSource() {
		this.id = getNextId();
		this.selected = new SimpleBooleanProperty(false);
	}

	public int getId() {
		return id;
	}

	public int getNextId() {
		return nextId++;
	}

	@Override
	public String toString() {
		return ies.getName();
	}

	public IesProfile getIes() {
		return ies;
	}

	public void setIes(IesProfile ies) {
		this.ies = ies;
	}

	public void createPropertiesPanel(GuiHelper guiHelper) {
		propertiesPanel = new LightSourcePropertiesPanel(this, guiHelper);
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
