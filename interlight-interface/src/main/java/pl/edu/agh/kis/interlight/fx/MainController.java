package pl.edu.agh.kis.interlight.fx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pl.edu.agh.kis.interlight.fx.model.AbstractSceneObject;

public class MainController {

	@FXML
	private HBox newSceneBox;
	@FXML
	private TextField projectName;
	@FXML
	private Button btnNewScene;
	@FXML
	private ListView<AbstractSceneObject> listView;
	@FXML
	private TextField sceneWidth;
	@FXML
	private Spinner<Double> spinRoomHeight;
	@FXML
	private Pane canvasWrapper;
	@FXML
	private StackPane centerPane;
	@FXML
	private TextField sceneLength;
	@FXML
	private ListView<AbstractSceneObject> listViewLights;
	@FXML
	private Accordion accordion;
	@FXML
	private VBox roomPane;
	@FXML
	private VBox objectsPane;
	@FXML
	private VBox lightsPane;
	@FXML
	private ListView<String> listViewProjects;
	@FXML
	private Button btnOpenProject;
	@FXML
	private Tab tabInput;
	@FXML
	private HBox controlPane;
    @FXML
    private Button btnCreateRectangle;
    @FXML
    private Button btnCreateCircle;

	private GuiHelper guiHelper;

	@FXML
	private void initialize() {

		guiHelper = new GuiHelper();
		canvasWrapper.getChildren().clear();

		accordion.setDisable(true);
		canvasWrapper.setVisible(false);
		controlPane.setVisible(false);
		newSceneBox.setVisible(true);

		tabInput.setText("New/open scene");

		for (TitledPane pane : accordion.getPanes()) {
			pane.setExpanded(false);
		}
		
		btnCreateRectangle.setText(null);
		btnCreateRectangle.setGraphic(new ImageView(new Image(getClass()
				.getClassLoader().getResourceAsStream("img/32rectangle.png"))));
		btnCreateCircle.setText(null);
		btnCreateCircle.setGraphic(new ImageView(new Image(getClass()
				.getClassLoader().getResourceAsStream("img/32ellipse.png"))));

		// Room
		accordion.getPanes().get(0).expandedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						if (newValue) {
							guiHelper.enableRoomEditing();
						} else {
							guiHelper.disableRoomEditing();
						}
					}
				});
		// Objects
		accordion.getPanes().get(1).expandedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						if (newValue) {
							for (AbstractSceneObject aso : listView.getItems()) {
								aso.enableEventHandlers();
								aso.getSceneObject().toFront();
							}
						} else {
							for (AbstractSceneObject aso : listView.getItems()) {
								aso.getSceneObject().pseudoClassStateChanged(
										guiHelper.getSelectedClass(), false);
								aso.disableEventHandlers();
								aso.getSceneObject().toBack();
							}
							if (guiHelper.getSceneModel().getRoomBounds() != null) {
								guiHelper.getSceneModel().getRoomBounds()
										.toBack();
							}
							listView.getSelectionModel().clearSelection();
						}
					}
				});
		// Lights
		accordion.getPanes().get(2).expandedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						if (newValue) {
							for (AbstractSceneObject aso : listViewLights
									.getItems()) {
								aso.enableEventHandlers();
								aso.getSceneObject().toFront();
							}
						} else {
							for (AbstractSceneObject aso : listViewLights
									.getItems()) {
								aso.getSceneObject().pseudoClassStateChanged(
										guiHelper.getSelectedClass(), false);
								aso.disableEventHandlers();
								aso.getSceneObject().toBack();
							}
							if (guiHelper.getSceneModel().getRoomBounds() != null) {
								guiHelper.getSceneModel().getRoomBounds()
										.toBack();
							}
							listViewLights.getSelectionModel().clearSelection();
						}
					}
				});

		listView.setItems(guiHelper.getSceneModel().getObjects());
		listView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<AbstractSceneObject>() {
					@Override
					public void changed(
							ObservableValue<? extends AbstractSceneObject> observable,
							AbstractSceneObject oldValue,
							AbstractSceneObject newValue) {
						if (oldValue != null) {
							objectsPane.getChildren().remove(
									oldValue.getPropertiesPanel());
						}
						if (newValue != null) {
							objectsPane.getChildren().add(
									newValue.getPropertiesPanel());
							if (oldValue != null) {
								oldValue.getSceneObject()
										.pseudoClassStateChanged(
												guiHelper.getSelectedClass(),
												false);
							}
							newValue.getSceneObject().pseudoClassStateChanged(
									guiHelper.getSelectedClass(), true);
						}
					}
				});
		listViewLights.setItems(guiHelper.getSceneModel().getLightSources());
		listViewLights.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<AbstractSceneObject>() {
					@Override
					public void changed(
							ObservableValue<? extends AbstractSceneObject> observable,
							AbstractSceneObject oldValue,
							AbstractSceneObject newValue) {
						if (oldValue != null) {
							lightsPane.getChildren().remove(
									oldValue.getPropertiesPanel());
						}
						if (newValue != null) {
							lightsPane.getChildren().add(
									newValue.getPropertiesPanel());
							if (oldValue != null) {
								oldValue.getSceneObject()
										.pseudoClassStateChanged(
												guiHelper.getSelectedClass(),
												false);
							}
							newValue.getSceneObject().pseudoClassStateChanged(
									guiHelper.getSelectedClass(), true);
						}
					}
				});

		sceneWidth.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				sceneWidth.pseudoClassStateChanged(guiHelper.getErrorClass(),
						false);
				btnNewScene.setDisable(false);
				try {
					Double value = Double.parseDouble(newValue);
					if (value < 1.0) {
						throw new NumberFormatException();
					}
					sceneLength.setText(String.valueOf(value
							* GuiHelper.CANVAS_HEIGHT / GuiHelper.CANVAS_WIDTH));
				} catch (NumberFormatException nfe) {
					sceneWidth.pseudoClassStateChanged(
							guiHelper.getErrorClass(), true);
					btnNewScene.setDisable(true);
				}
			}
		});

		sceneLength.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				sceneLength.pseudoClassStateChanged(guiHelper.getErrorClass(),
						false);
				btnNewScene.setDisable(false);
				try {
					Double value = Double.parseDouble(newValue);
					if (value < 1.0) {
						throw new NumberFormatException();
					}
					sceneWidth.setText(String.valueOf(value
							* GuiHelper.CANVAS_WIDTH / GuiHelper.CANVAS_HEIGHT));
				} catch (NumberFormatException nfe) {
					sceneLength.pseudoClassStateChanged(
							guiHelper.getErrorClass(), true);
					btnNewScene.setDisable(true);
				}
			}
		});

		spinRoomHeight = new Spinner<Double>(0.0, 10.0,
				GuiHelper.DEFAULT_HEIGHT, 0.5);
		spinRoomHeight.setEditable(true);
		spinRoomHeight.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinRoomHeight.pseudoClassStateChanged(
								guiHelper.errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							guiHelper.getSceneModel().setRoomHeightM(value);
						} catch (NumberFormatException nfe) {
							spinRoomHeight.pseudoClassStateChanged(
									guiHelper.errorClass, true);
						}
					}
				});

		projectName.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				projectName.pseudoClassStateChanged(guiHelper.getErrorClass(),
						false);
				btnNewScene.setDisable(false);
				if (newValue == null || newValue.trim().isEmpty()) {
					projectName.pseudoClassStateChanged(
							guiHelper.getErrorClass(), true);
					btnNewScene.setDisable(true);
				}
			}
		});

		listViewProjects.setItems(guiHelper.createProjectsList());
		listViewProjects.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						if (newValue != null) {
							btnOpenProject.setDisable(false);
						}
					}
				});

		projectName.setText(guiHelper.createNewProjectName());
		sceneLength.setText("3.0");
		sceneWidth.setText("5.0");
	}

	@FXML
	private void newScene(ActionEvent event) {

		controlPane.setVisible(true);

		accordion.setDisable(false);
		accordion.getPanes().get(0).setExpanded(true);

		guiHelper.getSceneModel().setSceneWidthM(
				Double.parseDouble(sceneWidth.getText()));
		guiHelper.getSceneModel().setSceneLengthM(
				Double.parseDouble(sceneLength.getText()));
		guiHelper.getSceneModel().setRoomHeightM(GuiHelper.DEFAULT_HEIGHT);
		GuiHelper.SCALE_PX_TO_M = guiHelper.getSceneModel().getSceneWidthM()
				/ GuiHelper.CANVAS_WIDTH;
		GuiHelper.SCALE_M_TO_PX = GuiHelper.CANVAS_WIDTH
				/ guiHelper.getSceneModel().getSceneWidthM();

		newSceneBox.setVisible(false);
		canvasWrapper.setVisible(true);

		canvasWrapper.getChildren().add(guiHelper.createCanvas());

		guiHelper.drawRulers(canvasWrapper);

		tabInput.setText(projectName.getText());

		guiHelper.getSceneModel().getRoom().createPropertiesPanel(guiHelper);
		roomPane.getChildren().clear();
		roomPane.getChildren().add(
				guiHelper.getSceneModel().getRoom().getPropertiesPanel());
		guiHelper.createDrawSceneHandler();

	}

	@FXML
	private void createRectangle(ActionEvent event) {
		guiHelper.createRectangle(listView);
	}

	@FXML
	void createCircle(ActionEvent event) {
		guiHelper.createEllipse(listView);
	}

	@FXML
	void resetRoom(ActionEvent event) {
		guiHelper.getSceneModel().createBounds(guiHelper.getCanvas());
	}

	@FXML
	void createIesLightSource(ActionEvent event) {
		guiHelper.createLightSource(listViewLights, true);
	}

	@FXML
	void createLightSource(ActionEvent event) {
		guiHelper.createLightSource(listViewLights, false);
	}

	@FXML
	void openProject(ActionEvent event) {
		// TODO
		// listViewProjects
	}

	@FXML
	void menuAbout(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("InterLight");
		alert.setContentText("AGH-UST 2015\nJakub Czajkowski, Bart�omiej Gnojek, Rafa� Salawa");
		alert.showAndWait();
	}

	@FXML
	void menuClose(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void menuNewOpen(ActionEvent event) {
		initialize();
	}

}
