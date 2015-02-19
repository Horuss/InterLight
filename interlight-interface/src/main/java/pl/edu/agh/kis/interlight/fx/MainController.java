package pl.edu.agh.kis.interlight.fx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.edu.agh.kis.interlight.fx.model.AbstractSceneObject;
import pl.edu.agh.kis.interlight.fx.model.Cuboid;
import pl.edu.agh.kis.interlight.fx.model.Cylinder;
import pl.edu.agh.kis.interlight.fx.model.SceneModel;
import pl.edu.agh.kis.interlight.fx.panel.CuboidPropertiesPanel;

public class MainController {

	@FXML
	private StackPane canvas;
	@FXML
	private Button btnCircle;
	@FXML
	private Button btnRectangle;
	@FXML
	private ListView<AbstractSceneObject> listView;
	@FXML
	private VBox rightPanel;

	private SceneModel sceneModel;

	private static final Logger logger = LogManager
			.getLogger(MainController.class.getName());

	@FXML
	private void initialize() {
		sceneModel = new SceneModel();
		listView.setItems(sceneModel.getObjects());
		listView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<AbstractSceneObject>() {
					@Override
					public void changed(
							ObservableValue<? extends AbstractSceneObject> observable,
							AbstractSceneObject oldValue,
							AbstractSceneObject newValue) {
						logger.info("zalogowany select");
						System.out.println("selected!");
						if (rightPanel.getChildren().size() > 1) {
							rightPanel.getChildren().remove(1);
						}
						if (newValue instanceof Cuboid) {
							Cuboid c = (Cuboid) newValue;
							CuboidPropertiesPanel cuboidPropertiesPanel = new CuboidPropertiesPanel(
									c);
							rightPanel.getChildren().add(cuboidPropertiesPanel);
						} else if (newValue instanceof Cylinder) {

						} else {
							throw new UnsupportedOperationException(
									"cant handle obj");
						}
					}
				});
	}

	@FXML
	private void createRectangle(ActionEvent event) {

		Cuboid cuboid = new Cuboid();
		cuboid.setHeight(1.);
		Rectangle r = new Rectangle();
		r.setX(50);
		r.setY(50);
		r.setWidth(200);
		r.setHeight(100);
		r.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						System.out.println("mouseClicked");
						listView.getSelectionModel().select(cuboid);
						event.consume();
					}
				});
		cuboid.setRectangle(r);
		sceneModel.getObjects().add(cuboid);
		canvas.getChildren().add(r);

	}

	@FXML
	void createCircle(ActionEvent event) {
		Circle c = new Circle();
		c.setCenterX(100.0f);
		c.setCenterY(100.0f);
		c.setRadius(100.0f);
		canvas.getChildren().add(c);
	}

}
