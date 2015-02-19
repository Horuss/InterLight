package pl.edu.agh.kis.interlight.fx.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.agh.kis.interlight.fx.model.Cuboid;

public class CuboidPropertiesPanel extends AbstractPropertiesPanel {

	private TextField tfX;
	private TextField tfY;
	private TextField tfWidth;
	private TextField tfLength;

	public CuboidPropertiesPanel(Cuboid cuboid) {
		super(cuboid);

		add(new Label("X:"), 0, 1);
		tfX = new TextField(String.valueOf(cuboid.getRectangle().getX()));
		add(tfX, 1, 1);
		tfX.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				tfX.pseudoClassStateChanged(errorClass, false);
				try {
					Double value = Double.parseDouble(newValue);
					cuboid.getRectangle().setTranslateX(
							value - cuboid.getRectangle().getX());
					logger.info("parsed new height " + value);
				} catch (NumberFormatException nfe) {
					tfX.pseudoClassStateChanged(errorClass, true);
					logger.info("not parsed " + newValue);
				}
			}
		});

		add(new Label("Y:"), 0, 2);
		tfY = new TextField(String.valueOf(cuboid.getRectangle().getY()));
		add(tfY, 1, 2);
		tfY.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				tfY.pseudoClassStateChanged(errorClass, false);
				try {
					Double value = Double.parseDouble(newValue);
					cuboid.getRectangle().setTranslateY(
							value - cuboid.getRectangle().getY());
					logger.info("parsed new height " + value);
				} catch (NumberFormatException nfe) {
					tfY.pseudoClassStateChanged(errorClass, true);
					logger.info("not parsed " + newValue);
				}
			}
		});

		add(new Label("Width:"), 0, 3);
		tfWidth = new TextField(
				String.valueOf(cuboid.getRectangle().getWidth()));
		add(tfWidth, 1, 3);
		tfWidth.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				tfWidth.pseudoClassStateChanged(errorClass, false);
				try {
					Double value = Double.parseDouble(newValue);
					cuboid.getRectangle().setWidth(value);
					logger.info("parsed new height " + value);
				} catch (NumberFormatException nfe) {
					tfWidth.pseudoClassStateChanged(errorClass, true);
					logger.info("not parsed " + newValue);
				}
			}
		});

		add(new Label("Length:"), 0, 4);
		tfLength = new TextField(String.valueOf(cuboid.getRectangle()
				.getHeight()));
		add(tfLength, 1, 4);
		tfLength.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				tfLength.pseudoClassStateChanged(errorClass, false);
				try {
					Double value = Double.parseDouble(newValue);
					cuboid.getRectangle().setHeight(value);
					logger.info("parsed new height " + value);
				} catch (NumberFormatException nfe) {
					tfLength.pseudoClassStateChanged(errorClass, true);
					logger.info("not parsed " + newValue);
				}
			}
		});
	}
}
