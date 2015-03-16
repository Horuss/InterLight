package pl.edu.agh.kis.interlight.fx.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.Cuboid;

public class CuboidPropertiesPanel extends AbstractPropertiesPanel {

	private Spinner<Double> spinX;
	private Spinner<Double> spinY;
	private Spinner<Double> spinWidth;
	private Spinner<Double> spinLength;

	public CuboidPropertiesPanel(Cuboid cuboid, GuiHelper guiHelper) {
		super(cuboid, guiHelper);

		add(new Label("Top-left X [m]:"), 0, 1);
		spinX = new Spinner<Double>(0.0, GuiHelper.CANVAS_WIDTH
				* GuiHelper.SCALE_PX_TO_M, cuboid.getRectangle().getLayoutX()
				* GuiHelper.SCALE_PX_TO_M, 0.1);
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
							cuboid.getRectangle().setLayoutX(
									value * GuiHelper.SCALE_M_TO_PX);
							spinX.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinX.pseudoClassStateChanged(errorClass, true);
						}
					}
				});
		// Bindings.bindBidirectional(spinX.getEditor().textProperty(),
		// cuboid.getRectangle().layoutXProperty(), new
		// NumberStringConverter());

		add(new Label("Top-left Y [m]:"), 0, 2);
		spinY = new Spinner<Double>(0.0, GuiHelper.CANVAS_HEIGHT
				* GuiHelper.SCALE_PX_TO_M, cuboid.getRectangle().getLayoutY()
				* GuiHelper.SCALE_PX_TO_M, 0.1);
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
							cuboid.getRectangle().setLayoutY(
									value * GuiHelper.SCALE_M_TO_PX);
							spinY.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinY.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

		add(new Label("Width [m]:"), 0, 3);
		spinWidth = new Spinner<Double>(0.0, GuiHelper.CANVAS_WIDTH
				* GuiHelper.SCALE_PX_TO_M, cuboid.getRectangle().getWidth()
				* GuiHelper.SCALE_PX_TO_M, 0.1);
		spinWidth.getStyleClass().add(
				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
		add(spinWidth, 1, 3);
		spinWidth.setEditable(true);
		spinWidth.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinWidth.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							cuboid.getRectangle().setWidth(
									value * GuiHelper.SCALE_M_TO_PX);
							spinWidth.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinWidth.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

		add(new Label("Length [m]:"), 0, 4);
		spinLength = new Spinner<Double>(0.0, GuiHelper.CANVAS_HEIGHT
				* GuiHelper.SCALE_PX_TO_M, cuboid.getRectangle().getHeight()
				* GuiHelper.SCALE_PX_TO_M, 0.1);
		add(spinLength, 1, 4);
		spinLength.setEditable(true);
		spinLength.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinLength.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							cuboid.getRectangle().setHeight(
									value * GuiHelper.SCALE_M_TO_PX);
							spinLength.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinLength
									.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

		createDeleteButton(cuboid, 5);
	}

	@Override
	public void updatePosition(Double x, Double y) {
		spinX.getValueFactory().setValue(x);
		spinY.getValueFactory().setValue(y);
	}
}
