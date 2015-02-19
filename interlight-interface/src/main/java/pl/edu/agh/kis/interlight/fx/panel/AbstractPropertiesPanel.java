package pl.edu.agh.kis.interlight.fx.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.edu.agh.kis.interlight.fx.model.AbstractSceneObject;

public abstract class AbstractPropertiesPanel extends GridPane {

	protected static final Logger logger = LogManager
			.getLogger(AbstractPropertiesPanel.class.getName());
	protected final PseudoClass errorClass = PseudoClass
			.getPseudoClass("error");

	protected TextField tfHeight;

	public AbstractPropertiesPanel(AbstractSceneObject object) {

		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));

		add(new Label("Height:"), 0, 0);
		tfHeight = new TextField(object.getHeight().toString());
		add(tfHeight, 1, 0);
		tfHeight.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				tfHeight.pseudoClassStateChanged(errorClass, false);
				try {
					Double height = Double.parseDouble(newValue);
					object.setHeight(height);
					logger.info("parsed new height " + height);
				} catch (NumberFormatException nfe) {
					tfHeight.pseudoClassStateChanged(errorClass, true);
					logger.info("not parsed " + newValue);
				}
			}
		});

	}

}
