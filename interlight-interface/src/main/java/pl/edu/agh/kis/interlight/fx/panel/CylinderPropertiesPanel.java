package pl.edu.agh.kis.interlight.fx.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.Cylinder;

public class CylinderPropertiesPanel extends AbstractPropertiesPanel {

	private Spinner<Double> spinCenterX;
	private Spinner<Double> spinCenterY;
	private Spinner<Double> spinRadiusX;
	private Spinner<Double> spinRadiusY;

	public CylinderPropertiesPanel(Cylinder cylinder, GuiHelper guiHelper) {
		super(cylinder, guiHelper);

		add(new Label("Center X [m]:"), 0, 1);
		spinCenterX = new Spinner<Double>(0.0, GuiHelper.CANVAS_WIDTH
				* GuiHelper.SCALE_PX_TO_M, cylinder.getSceneObject()
				.getLayoutX() * GuiHelper.SCALE_PX_TO_M, 0.1);
		spinCenterX.getStyleClass().add(
				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
		add(spinCenterX, 1, 1);
		spinCenterX.setEditable(true);
		spinCenterX.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinCenterX.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							cylinder.getSceneObject().setLayoutX(
									value * GuiHelper.SCALE_M_TO_PX);
							spinCenterX.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinCenterX.pseudoClassStateChanged(errorClass,
									true);
						}
					}
				});

		add(new Label("Center Y [m]:"), 0, 2);
		spinCenterY = new Spinner<Double>(0.0, GuiHelper.CANVAS_HEIGHT
				* GuiHelper.SCALE_PX_TO_M, cylinder.getSceneObject()
				.getLayoutY() * GuiHelper.SCALE_PX_TO_M, 0.1);
		add(spinCenterY, 1, 2);
		spinCenterY.setEditable(true);
		spinCenterY.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinCenterY.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							cylinder.getSceneObject().setLayoutY(
									value * GuiHelper.SCALE_M_TO_PX);
							spinCenterY.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinCenterY.pseudoClassStateChanged(errorClass,
									true);
						}
					}
				});

		add(new Label("Radius X [m]:"), 0, 3);
		spinRadiusX = new Spinner<Double>(0.0, GuiHelper.CANVAS_WIDTH
				* GuiHelper.SCALE_PX_TO_M, cylinder.getEllipse().getRadiusX()
				* GuiHelper.SCALE_PX_TO_M, 0.1);
		spinRadiusX.getStyleClass().add(
				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
		add(spinRadiusX, 1, 3);
		spinRadiusX.setEditable(true);
		spinRadiusX.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinRadiusX.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							cylinder.getEllipse().setRadiusX(
									value * GuiHelper.SCALE_M_TO_PX);
							spinRadiusX.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinRadiusX.pseudoClassStateChanged(errorClass,
									true);
						}
					}
				});

		add(new Label("Radius Y [m]:"), 0, 4);
		spinRadiusY = new Spinner<Double>(0.0, GuiHelper.CANVAS_HEIGHT
				* GuiHelper.SCALE_PX_TO_M, cylinder.getEllipse().getRadiusY()
				* GuiHelper.SCALE_PX_TO_M, 0.1);
		add(spinRadiusY, 1, 4);
		spinRadiusY.setEditable(true);
		spinRadiusY.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinRadiusY.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							cylinder.getEllipse().setRadiusY(
									value * GuiHelper.SCALE_M_TO_PX);
							spinRadiusY.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinRadiusY.pseudoClassStateChanged(errorClass,
									true);
						}
					}
				});

		createWorkspaceCheckbox(cylinder, 5);
		createDeleteButton(cylinder, 6);
	}

	@Override
	public void updatePosition(Double x, Double y) {
		spinCenterX.getValueFactory().setValue(x);
		spinCenterY.getValueFactory().setValue(y);
	}
}
