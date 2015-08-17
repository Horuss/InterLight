package pl.edu.agh.kis.interlight.fx;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pl.edu.agh.kis.interlight.datamodel.ICuboid;
import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.IRoom;
import pl.edu.agh.kis.interlight.datamodel.IScene;
import pl.edu.agh.kis.interlight.datamodel.ISolution;
import pl.edu.agh.kis.interlight.datamodel.sets.ISceneSet;
import pl.edu.agh.kis.interlight.datamodel.util.IPoint;
import pl.edu.agh.kis.interlight.fx.model.AbstractSceneObject;
import pl.edu.agh.kis.interlight.fx.model.LightPoint;
import pl.edu.agh.kis.interlight.fx.model.LightSource;
import pl.edu.agh.kis.interlight.fx.model.SceneShape;
import pl.edu.agh.kis.interlight.fx.parts.BoundsAnchor;

public class MainController {

	@FXML
	private TabPane mainTabPane;
	@FXML
	private HBox newSceneBox;
	@FXML
	private TextField projectName;
	@FXML
	private Button btnNewScene;
	@FXML
	private ListView<SceneShape> listView;
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
	private ListView<LightPoint> listViewLightPoints;
	@FXML
	private TableView<LightSource> tableViewLightSources;
	@FXML
	private Accordion accordion;
	@FXML
	private VBox roomPane;
	@FXML
	private VBox objectsPane;
	@FXML
	private VBox lightPointsPane;
	@FXML
	private VBox lightSourcesPane;
	@FXML
	private ListView<String> listViewProjects;
	@FXML
	private Button btnOpenProject;
	@FXML
	private Tab tabInput;
	@FXML
	private Tab tabOutput;
	@FXML
	private HBox controlPane;
	@FXML
	private Button btnCreateRectangle;
	@FXML
	private Button btnCreateCircle;
	@FXML
	private TableView<ISolution> tableOutput;
	@FXML
	private TitledPane solutionDetailsPane;

	private GuiHelper guiHelper;
	
	private void reset() {
		
		guiHelper = new GuiHelper();
		
		canvasWrapper.getChildren().clear();

		accordion.setDisable(true);
		canvasWrapper.setVisible(false);
		controlPane.setVisible(false);
		newSceneBox.setVisible(true);

		mainTabPane.getSelectionModel().select(tabInput);
		tabInput.setText("New/open scene");
		tabOutput.setDisable(true);

		for (TitledPane pane : accordion.getPanes()) {
			pane.setExpanded(false);
		}
	}

	@FXML
	private void initialize() {
		
		reset();

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
							guiHelper.setHintMessage("");
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
		// Light points
				accordion.getPanes().get(2).expandedProperty()
						.addListener(new ChangeListener<Boolean>() {
							@Override
							public void changed(
									ObservableValue<? extends Boolean> observable,
									Boolean oldValue, Boolean newValue) {
								if (newValue) {
									guiHelper.setHintMessage("");
									for (AbstractSceneObject aso : listViewLightPoints
											.getItems()) {
										aso.enableEventHandlers();
										aso.getSceneObject().toFront();
									}
								} else {
									for (AbstractSceneObject aso : listViewLightPoints
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
									listViewLightPoints.getSelectionModel()
											.clearSelection();
								}
							}
						});
		// Light sources
		accordion.getPanes().get(3).expandedProperty()
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						if (newValue) {
							guiHelper.setHintMessage("");
						} 
					}
				});

		
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
		
		listViewLightPoints.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<AbstractSceneObject>() {
					@Override
					public void changed(
							ObservableValue<? extends AbstractSceneObject> observable,
							AbstractSceneObject oldValue,
							AbstractSceneObject newValue) {
						if (oldValue != null) {
							lightPointsPane.getChildren().remove(
									oldValue.getPropertiesPanel());
						}
						if (newValue != null) {
							lightPointsPane.getChildren().add(
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

		TableColumn<LightSource, Boolean> selectedCol = new TableColumn<>("");
		selectedCol.setEditable(true);
		selectedCol.setResizable(false);
		selectedCol.setPrefWidth(30);
		selectedCol
				.setCellValueFactory(new Callback<CellDataFeatures<LightSource, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(
							CellDataFeatures<LightSource, Boolean> p) {
						return p.getValue().getSelected();
					}
				});
		selectedCol
				.setCellFactory(new Callback<TableColumn<LightSource, Boolean>, TableCell<LightSource, Boolean>>() {
					@Override
					public TableCell<LightSource, Boolean> call(
							TableColumn<LightSource, Boolean> p) {
						return new CheckBoxTableCell<>();
					}
				});
		TableColumn<LightSource, String> nameCol = new TableColumn<>("Name");
		nameCol.setPrefWidth(195);
		nameCol.setCellValueFactory(new Callback<CellDataFeatures<LightSource, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(
					CellDataFeatures<LightSource, String> p) {
				return new ReadOnlyObjectWrapper<String>(p.getValue()
						.toString());
			}
		});
		tableViewLightSources.getColumns().add(selectedCol);
		tableViewLightSources.getColumns().add(nameCol);
		tableViewLightSources.setEditable(true);
		tableViewLightSources.setPlaceholder(new Label(""));
		tableViewLightSources.setItems(guiHelper.getSceneModel()
				.getLightSources());
		tableViewLightSources.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<LightSource>() {
					@Override
					public void changed(
							ObservableValue<? extends LightSource> observable,
							LightSource oldValue, LightSource newValue) {
						if (oldValue != null) {
							lightSourcesPane.getChildren().remove(
									oldValue.getPropertiesPanel());
						}
						if (newValue != null) {
							lightSourcesPane.getChildren().add(
									newValue.getPropertiesPanel());
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
								GuiHelper.errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							guiHelper.getSceneModel().setRoomHeightM(value);
						} catch (NumberFormatException nfe) {
							spinRoomHeight.pseudoClassStateChanged(
									GuiHelper.errorClass, true);
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
		
		guiHelper.getSceneModel().setName(projectName.getText());
		guiHelper.getSceneModel().setSceneWidthM(
				Double.parseDouble(sceneWidth.getText()));
		guiHelper.getSceneModel().setSceneLengthM(
				Double.parseDouble(sceneLength.getText()));
		guiHelper.getSceneModel().setRoomHeightM(GuiHelper.DEFAULT_HEIGHT);

		load();
		
		guiHelper.setHintMessage(GuiHelper.HINT_ROOM_INIT);
	}

	@FXML
	void createRectangle(ActionEvent event) {
		guiHelper.createRectangle(listView);
	}

	@FXML
	void createCircle(ActionEvent event) {
		guiHelper.createEllipse(listView);
	}

	@FXML
	void createLightSource(ActionEvent event) {
		guiHelper.createLightSource();
	}

	@FXML
	void createLightPoint(ActionEvent event) {
		guiHelper.createLightPoint(listViewLightPoints, null, null);
	}
	
	@FXML
	void createLightPointsNet(ActionEvent event) {
		guiHelper.createLightPointsNet(listViewLightPoints);
	}

	@FXML
	void openProject(ActionEvent event) {
		
		String projectName = listViewProjects.getSelectionModel().getSelectedItem();
		// TODO
		// 1. use interlight-model method to deserialize json into common-datamodel
		// TODO only stub now
		ISceneSet sceneSet = new ISceneSet();
		sceneSet.setScene(new IScene(projectName, 8.0, 6.0));
		IRoom room = new IRoom(10.0);
		room.getPoints().add(new IPoint(1.0, 1.0));
		room.getPoints().add(new IPoint(5.0, 1.0));
		room.getPoints().add(new IPoint(4.0, 4.0));
		sceneSet.setRoom(room);
		sceneSet.getLightPoints().add(new ILightPoint(1, new IPoint(5.0, 5.0), 9.0));
		sceneSet.getCuboids().add(new ICuboid(new IPoint(2.0, 2.0), 4.0, 1.0, 1.0, 0, true));
		// 2. convert common-datamodel to interface datamodel
		guiHelper.modelToGui(sceneSet);
		// 3. create scene from converted objects
		
		load();
	}

	@FXML
	void menuAbout(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("InterLight");
		alert.setContentText("AGH-UST 2015\nJakub Czajkowski, Bart³omiej Gnojek, Rafa³ Salawa");
		alert.showAndWait();
	}
	
	@FXML
	void menuHelp(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.getButtonTypes().removeAll(ButtonType.CANCEL);
		alert.setTitle("Help");
		alert.setHeaderText("InterLight");
		alert.setContentText("Design indoor lighting with InterLight!\n\n"
				+ "1) Room: draw room boundaries, add with left mouse button and remove with right.\n\n"
				+ "2) Objects: add them using buttons, move using \"drag and drop\" or edit properties of selected one manually.\n\n"
				+ "3) Light points: add them using buttons, single or whole net.\n\n"
				+ "4) Light sources: add them with IES file and select those, that you want to be considered by optimalization.\n\n"
				+ "Run optimalization to find best solutions!");
		alert.showAndWait();
	}

	@FXML
	void menuClose(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void menuNewOpen(ActionEvent event) {
		reset();
	}
	
	@FXML
	void menuSave(ActionEvent event) {
		save();
	}

	@FXML
	void btnSave(ActionEvent event) {
		save();
	}
	
	private void save() {
		// TODO
		// 1. convert gui model to common-datamodel
		guiHelper.guiToModel();
		// 2. use interlight-model method to create json scene from common-datamodel
		// 3. save it to file in project folder
	}
	
	@FXML
	void calculate(ActionEvent event) {
		// TODO
		// 1. convert gui model to common-datamodel 
		guiHelper.guiToModel();
		// 2. use interlight-model method to create json scene from common-datamodel
		// 3. use interlight-model method to create radiance input from json
		// 4. convert radiance output to common-datamodel
		// 5. use interlight-opt method to create solutions using json scene radiance output
		// 6. allow to visualize each solution (radiance, heatmap)
		
		// Stub:
		// TODO
		tabOutput.setDisable(false);
		guiHelper.fillOutputTab(tableOutput, solutionDetailsPane);
		mainTabPane.getTabs().add(tabOutput);
		mainTabPane.getSelectionModel().select(tabOutput);
	}

	@FXML
	void btnEditJson(ActionEvent event) {
		// TODO
		// 1. convert gui model to common-datamodel 
		guiHelper.guiToModel();
		// 2. use interlight-model method to create json scene from common-datamodel
		// 3. display new window: json editor with validation functionality
		//	textarea z jsonem, example w formie helpa z boku, buttony: validacje i zatwierdzenie
	}
	
	private void load() {
		
		tabInput.setText(guiHelper.getSceneModel().getName());
		controlPane.setVisible(true);

		accordion.setDisable(false);
		accordion.getPanes().get(0).setExpanded(true);
		
		GuiHelper.SCALE_PX_TO_M = guiHelper.getSceneModel().getSceneWidthM()
				/ GuiHelper.CANVAS_WIDTH;
		GuiHelper.SCALE_M_TO_PX = GuiHelper.CANVAS_WIDTH
				/ guiHelper.getSceneModel().getSceneWidthM();

		newSceneBox.setVisible(false);
		canvasWrapper.setVisible(true);

		canvasWrapper.getChildren().add(guiHelper.createCanvas());
		
		guiHelper.getSceneModel().getRoomBounds().setId("borders");
		guiHelper.getCanvas().getChildren().add(guiHelper.getSceneModel().getRoomBounds());
		guiHelper.getSceneModel().getRoom().getSceneObject().toBack();

		guiHelper.drawRulers(canvasWrapper);

		guiHelper.getSceneModel().getRoom().createPropertiesPanel(guiHelper);
		roomPane.getChildren().clear();
		roomPane.getChildren().add(
				guiHelper.getSceneModel().getRoom().getPropertiesPanel());
		guiHelper.createDrawSceneHandler();
		
		for (LightPoint lightPoint : guiHelper.getSceneModel().getLightPoints()) { 
			lightPoint.createPropertiesPanel(guiHelper);
			lightPoint.createEventHandlers(guiHelper, listViewLightPoints);
			guiHelper.getCanvas().getChildren().add(lightPoint.getSceneObject());
		}
		for (SceneShape sceneShape : guiHelper.getSceneModel().getShapes()) { 
			sceneShape.createPropertiesPanel(guiHelper);
			sceneShape.createEventHandlers(guiHelper, listView);
			sceneShape.getSceneObject().getStyleClass().add("sceneObj");
			guiHelper.getCanvas().getChildren().add(sceneShape.getSceneObject());
		}
		Double tmp = null;
		for (Double d : guiHelper.getSceneModel().getRoomBounds().getPoints()) {
			if (tmp == null) {
				tmp = d;
			} else {
				BoundsAnchor newAnchor = new BoundsAnchor(guiHelper, tmp, d);
				guiHelper.getAnchorsList().add(newAnchor);
				guiHelper.getCanvas().getChildren().add(newAnchor);
				tmp = null;
			}
		}
		
		listView.setItems(guiHelper.getSceneModel().getShapes());
		listViewLightPoints
				.setItems(guiHelper.getSceneModel().getLightPoints());
	}

}
