package pl.edu.agh.kis.interlight.fx.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.LightPoint;

public class LightPointPropertiesPanel extends AbstractPropertiesPanel {

	protected Spinner<Double> spinX;
	protected Spinner<Double> spinY;

	public LightPointPropertiesPanel(LightPoint lightPoint,
			GuiHelper guiHelper) {
		super(lightPoint, guiHelper);

		add(new Label("X [m]:"), 0, 1);
		spinX = new Spinner<Double>(0.0, GuiHelper.CANVAS_WIDTH
				* GuiHelper.SCALE_PX_TO_M, lightPoint.getSceneObject()
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
							lightPoint.getSceneObject().setLayoutX(
									value * GuiHelper.SCALE_M_TO_PX);
							spinX.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinX.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

		add(new Label("Y [m]:"), 0, 2);
		spinY = new Spinner<Double>(0.0, GuiHelper.CANVAS_HEIGHT
				* GuiHelper.SCALE_PX_TO_M, lightPoint.getSceneObject()
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
							lightPoint.getSceneObject().setLayoutY(
									value * GuiHelper.SCALE_M_TO_PX);
							spinY.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinY.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

		btnDelete = new Button("Delete");
		btnDelete.setPrefWidth(Double.MAX_VALUE);
		btnDelete.setGraphic(new ImageView(new Image(getClass()
				.getClassLoader().getResourceAsStream("img/16delete.png"))));
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				guiHelper.getSceneModel().getLightPoints().remove(lightPoint);
				guiHelper.getCanvas().getChildren()
						.remove(lightPoint.getSceneObject());

			}
		});
		add(btnDelete, 0, 3, 2, 1);
	}

	@Override
	public void updatePosition(Double x, Double y) {
		spinX.getValueFactory().setValue(x);
		spinY.getValueFactory().setValue(y);
	}
}
