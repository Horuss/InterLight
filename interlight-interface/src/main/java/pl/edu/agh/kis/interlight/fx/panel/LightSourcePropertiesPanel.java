package pl.edu.agh.kis.interlight.fx.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.LightSource;

public class LightSourcePropertiesPanel extends AbstractPropertiesPanel {

	protected Spinner<Double> spinX;
	protected Spinner<Double> spinY;
	protected Spinner<Double> spinPower;

	public LightSourcePropertiesPanel(LightSource lightSource,
			GuiHelper guiHelper) {
		super(lightSource, guiHelper);

		add(new Label("X [m]:"), 0, 1);
		spinX = new Spinner<Double>(0.0, GuiHelper.CANVAS_WIDTH
				* GuiHelper.SCALE_PX_TO_M, lightSource.getSceneObject()
				.getLayoutX() * GuiHelper.SCALE_PX_TO_M, 0.1);
		spinX.getStyleClass().add(
				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
		add(spinX, 1, 1);
		spinX.setEditable(true);
		spinX.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinX.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							lightSource.getSceneObject().setLayoutX(
									value * GuiHelper.SCALE_M_TO_PX);
							spinX.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinX.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

		add(new Label("Y [m]:"), 0, 2);
		spinY = new Spinner<Double>(0.0, GuiHelper.CANVAS_HEIGHT
				* GuiHelper.SCALE_PX_TO_M, lightSource.getSceneObject()
				.getLayoutY() * GuiHelper.SCALE_PX_TO_M, 0.1);
		add(spinY, 1, 2);
		spinY.setEditable(true);
		spinY.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinY.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							lightSource.getSceneObject().setLayoutY(
									value * GuiHelper.SCALE_M_TO_PX);
							spinY.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinY.pseudoClassStateChanged(errorClass, true);
						}
					}
				});
		
		add(new Label("Power [W]:"), 0, 3);
		spinPower = new Spinner<Double>(0.0, 1000.0, 40.0, 5.0);
		add(spinPower, 1, 3);
		spinPower.setEditable(true);
		spinPower.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinPower.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							lightSource.setPower(value);
							spinPower.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinPower.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

		createLightDeleteButton(lightSource);
	}
	
	protected void createLightDeleteButton(LightSource lightSource) {
		createDeleteButton(lightSource, 4);
	}

	@Override
	public void updatePosition(Double x, Double y) {
		spinX.getValueFactory().setValue(x);
		spinY.getValueFactory().setValue(y);
	}
}
