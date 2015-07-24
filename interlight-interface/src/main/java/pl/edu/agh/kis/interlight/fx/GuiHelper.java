package pl.edu.agh.kis.interlight.fx;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.ILightSource;
import pl.edu.agh.kis.interlight.datamodel.ISolution;
import pl.edu.agh.kis.interlight.datamodel.util.IPoint;
import pl.edu.agh.kis.interlight.fx.model.Cuboid;
import pl.edu.agh.kis.interlight.fx.model.Cylinder;
import pl.edu.agh.kis.interlight.fx.model.Ies;
import pl.edu.agh.kis.interlight.fx.model.LightPoint;
import pl.edu.agh.kis.interlight.fx.model.LightPointNet;
import pl.edu.agh.kis.interlight.fx.model.LightSource;
import pl.edu.agh.kis.interlight.fx.model.SceneModel;
import pl.edu.agh.kis.interlight.fx.model.SceneShape;
import pl.edu.agh.kis.interlight.fx.model.SolutionDetailsRow;
import pl.edu.agh.kis.interlight.fx.panel.RoomPropertiesPanel;
import pl.edu.agh.kis.interlight.fx.parts.BoundsAnchor;
import pl.edu.agh.kis.interlight.fx.parts.LightPointNetDialog;

public class GuiHelper {

	private static final Logger logger = LogManager.getLogger(GuiHelper.class
			.getName());

	private static final String USER_DIR = System.getProperty("user.dir");

	public static final DecimalFormat DF = new DecimalFormat("0.00");
	public static final double CANVAS_WIDTH = 640;
	public static final double CANVAS_HEIGHT = 480;
	public static final double DEFAULT_HEIGHT = 3.0;
	public static double SCALE_PX_TO_M;
	public static double SCALE_M_TO_PX;
	
	public static final String HINT_ROOM_INIT = "- click to add points\n- drag them to move around\n- right-click to remove";

	private Pane canvas;
	private SceneModel sceneModel;
	private Text hintText;

	public final static PseudoClass errorClass = PseudoClass
			.getPseudoClass("error");
	public final static PseudoClass selectedClass = PseudoClass
			.getPseudoClass("selected");

	private double orgSceneX;
	private double orgSceneY;
	private double orgTranslateX;
	private double orgTranslateY;

	private EventHandler<MouseEvent> drawRoomHandler;
	private final ObservableList<Ies> iesList;
	private final List<BoundsAnchor> anchorsList;

	public GuiHelper() {
		sceneModel = new SceneModel();
		iesList = createIesList();
		anchorsList = new LinkedList<>();
	}

	public String createNewProjectName() {
		int i = 1;
		String newProject = "NewProject";
		boolean created = false;
		while (!created) {
			if (!Files.exists(Paths.get(USER_DIR + "/projects/" + newProject
					+ i))) {
				newProject += i;
				created = true;
			}
			i++;
		}
		return newProject;
	}

	public ObservableList<String> createProjectsList() {
		ObservableList<String> list = FXCollections.observableArrayList();
		try (DirectoryStream<Path> directoryStream = Files
				.newDirectoryStream(Paths.get(USER_DIR + "/projects/"))) {
			for (Path path : directoryStream) {
				list.add(path.getFileName().toString());
			}
		} catch (IOException ex) {
			logger.error("Error creating projects list");
		}
		return list;
	}

	private ObservableList<Ies> createIesList() {
		ObservableList<Ies> list = FXCollections.observableArrayList();
		try (DirectoryStream<Path> directoryStream = Files
				.newDirectoryStream(Paths.get(USER_DIR + "/res/ies/"))) {
			for (Path path : directoryStream) {
				Ies ies = new Ies(path);
				list.add(ies);
			}
		} catch (IOException ex) {
			logger.error("Error creating IES list");
		}
		return list;
	}

	public Pane getCanvas() {
		return canvas;
	}

	public void setCanvas(Pane canvas) {
		this.canvas = canvas;
	}

	public SceneModel getSceneModel() {
		return sceneModel;
	}

	public void setSceneModel(SceneModel sceneModel) {
		this.sceneModel = sceneModel;
	}

	public EventHandler<MouseEvent> getDrawRoomHandler() {
		return drawRoomHandler;
	}

	public void createDrawSceneHandler() {
		this.drawRoomHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton() == MouseButton.PRIMARY) {
					RoomPropertiesPanel rPanel = (RoomPropertiesPanel) sceneModel
							.getRoom().getPropertiesPanel();
					rPanel.getBtnFit().setDisable(true);
					int polygonSize = sceneModel.getRoomBounds().getPoints().size();
					Double x = event.getX();
					Double y = event.getY();
	
					if (x < 0) {
						x = 0.0;
					}
					if (y < 0) {
						y = 0.0;
					}
					if (x > GuiHelper.CANVAS_WIDTH) {
						x = GuiHelper.CANVAS_WIDTH;
					}
					if (y > GuiHelper.CANVAS_HEIGHT) {
						y = GuiHelper.CANVAS_HEIGHT;
					}
	
					if (polygonSize > 0
							&& rPanel.getChkAlign().selectedProperty().get()) {
						if (Math.abs(sceneModel.getRoomBounds().getPoints()
								.get(polygonSize - 2)
								- x) < Math.abs(sceneModel.getRoomBounds()
								.getPoints().get(polygonSize - 1)
								- y)) {
							x = sceneModel.getRoomBounds().getPoints()
									.get(polygonSize - 2);
						} else {
							y = sceneModel.getRoomBounds().getPoints()
									.get(polygonSize - 1);
						}
	
					}
					addRoomPoint(x, y);
				}
				event.consume();
			}
		};
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, drawRoomHandler);
	}

	public void addRoomPoint(Double x, Double y) {
		
		hideHintMessage();
		
		sceneModel.getRoomBounds().getPoints().addAll(x, y);

		BoundsAnchor newAnchor = new BoundsAnchor(this, x, y);
		anchorsList.add(newAnchor);
		canvas.getChildren().add(newAnchor);

	}
	
	public void removeAnchor(BoundsAnchor a) {
		int indexOf = anchorsList.indexOf(a);
		a.updateDuringRemove();
		canvas.getChildren().remove(a.getLengthLabelNext());
		anchorsList.remove(indexOf);
		if(anchorsList.size() == 1) {
			canvas.getChildren().remove(a.getLengthLabelPrev());
		}
		canvas.getChildren().remove(a.getLabel());
		canvas.getChildren().remove(a);
		((Polygon)sceneModel.getRoom().getSceneObject()).getPoints().remove(indexOf * 2); 
		((Polygon)sceneModel.getRoom().getSceneObject()).getPoints().remove(indexOf * 2); 
	}

	public PseudoClass getErrorClass() {
		return errorClass;
	}

	public PseudoClass getSelectedClass() {
		return selectedClass;
	}

	public Double getSceneWidthPx() {
		return CANVAS_WIDTH;
	}

	public Double getSceneLengthPx() {
		return CANVAS_HEIGHT;
	}

	public void drawRulers(Pane canvasWrapper) {
		Line rulerX = new Line(0, canvas.getPrefHeight() + 15.,
				canvas.getPrefWidth(), canvas.getPrefHeight() + 15.);
		canvasWrapper.getChildren().add(rulerX);
		Label rulerX1 = new Label(DF.format(0.0) + " m");
		rulerX1.setLayoutX(0);
		rulerX1.setLayoutY(canvas.getPrefHeight() + 20.);
		canvasWrapper.getChildren().add(rulerX1);
		Label rulerX2 = new Label(DF.format(sceneModel.getSceneWidthM()) + " m");
		rulerX2.setLayoutX(canvas.getPrefWidth() - 20.);
		rulerX2.setLayoutY(canvas.getPrefHeight() + 20.);
		canvasWrapper.getChildren().add(rulerX2);

		Line rulerY = new Line(canvas.getPrefWidth() + 15., 0,
				canvas.getPrefWidth() + 15, canvas.getPrefHeight());
		canvasWrapper.getChildren().add(rulerY);
		Label rulerY1 = new Label(DF.format(0.0) + " m");
		rulerY1.setLayoutX(canvas.getPrefWidth() + 20.);
		rulerY1.setLayoutY(0);
		canvasWrapper.getChildren().add(rulerY1);
		Label rulerY2 = new Label(DF.format(sceneModel.getSceneLengthM())
				+ " m");
		rulerY2.setLayoutX(canvas.getPrefWidth() + 20.);
		rulerY2.setLayoutY(canvas.getPrefHeight() - 20.);
		canvasWrapper.getChildren().add(rulerY2);
	}

	public Polygon initPolygon(Pane canvas) {
		Polygon polygon = new Polygon();
		polygon.setId("borders");
		canvas.getChildren().add(polygon);
		return polygon;
	}

	public Pane createCanvas() {
		canvas = new Pane();
		canvas.setId("canvas");
		canvas.setPrefHeight(GuiHelper.CANVAS_HEIGHT);
		canvas.setPrefWidth(GuiHelper.CANVAS_WIDTH);
		hintText = new Text();
		hintText.setLayoutY(GuiHelper.CANVAS_HEIGHT / 4);
		hintText.setLayoutX(200);
		hintText.setFill(Color.GRAY);
		hintText.setFont(Font.font(20));
		canvas.getChildren().add(hintText);
		setHintMessage(HINT_ROOM_INIT);
		sceneModel.createBounds(canvas);
		return canvas;
	}
	
	public void setHintMessage(String content) {
		hintText.setText(content);
		hintText.setVisible(true);
	}
	
	public void hideHintMessage() {
		hintText.setVisible(false);
	}

	public void createRectangle(ListView<SceneShape> listView) {
		Cuboid cuboid = new Cuboid();
		cuboid.setHeight(1.);
		Rectangle r = new Rectangle();
		r.getStyleClass().add("sceneObj");
		r.setLayoutX((cuboid.getId() / 10.0) * GuiHelper.SCALE_M_TO_PX);
		r.setLayoutY((cuboid.getId() / 10.0) * GuiHelper.SCALE_M_TO_PX);
		r.setWidth(1.0 * GuiHelper.SCALE_M_TO_PX);
		r.setHeight(0.5 * GuiHelper.SCALE_M_TO_PX);
		cuboid.setRectangle(r);
		cuboid.createPropertiesPanel(this);
		cuboid.createEventHandlers(this, listView);
		cuboid.enableEventHandlers();
		sceneModel.getShapes().add(cuboid);
		canvas.getChildren().add(r);
	}

	public void createEllipse(ListView<SceneShape> listView) {
		Cylinder cylinder = new Cylinder();
		cylinder.setHeight(1.);
		Ellipse e = new Ellipse();
		e.getStyleClass().add("sceneObj");
		e.setLayoutX((0.5 + cylinder.getId() / 10.0) * GuiHelper.SCALE_M_TO_PX);
		e.setLayoutY((0.5 + cylinder.getId() / 10.0) * GuiHelper.SCALE_M_TO_PX);
		e.setRadiusX(0.5 * GuiHelper.SCALE_M_TO_PX);
		e.setRadiusY(0.5 * GuiHelper.SCALE_M_TO_PX);
		cylinder.setEllipse(e);
		cylinder.createPropertiesPanel(this);
		cylinder.createEventHandlers(this, listView);
		cylinder.enableEventHandlers();
		sceneModel.getShapes().add(cylinder);
		canvas.getChildren().add(e);
	}

	public void createLightPoint(ListView<LightPoint> listViewLights, Double x, Double y) {
		LightPoint lightPoint = new LightPoint(sceneModel.getRoomHeightM(), x, y);
		lightPoint.createPropertiesPanel(this);
		lightPoint.createEventHandlers(this, listViewLights);
		lightPoint.enableEventHandlers();
		sceneModel.getLightPoints().add(lightPoint);
		canvas.getChildren().add(lightPoint.getSceneObject());
	}
	
	public void createLightPointsNet(ListView<LightPoint> listViewLights) {
		LightPointNetDialog lightPointNetDialog = new LightPointNetDialog(sceneModel.getSceneWidthM(), sceneModel.getSceneLengthM());
		Optional<LightPointNet> result = lightPointNetDialog.showAndWait();
		if(result.isPresent()) {
			LightPointNet conf = result.get();
			Integer origAmountY = new Integer(conf.getAmountY());
			Double origMarginY = new Double(conf.getMarginY());
			while(conf.getAmountX() > 0 && conf.getMarginX() <= sceneModel.getSceneWidthM()) {
				conf.setAmountY(origAmountY);
				conf.setMarginY(origMarginY);
				while(conf.getAmountY() > 0 && conf.getMarginY() <= sceneModel.getSceneLengthM()) {
					createLightPoint(listViewLights, conf.getMarginX(), conf.getMarginY());
					conf.setMarginY(conf.getMarginY() + conf.getSpacingY());
					conf.setAmountY(conf.getAmountY() - 1);
				}
				conf.setMarginX(conf.getMarginX() + conf.getSpacingX());
				conf.setAmountX(conf.getAmountX() - 1);
			}
		}
	}

	public void createLightSource() {
		LightSource lightSource = null;
		lightSource = new LightSource();
		lightSource.createPropertiesPanel(this);
		sceneModel.getLightSources().add(lightSource);
	}

	public void enableRoomEditing() {
		if (canvas != null) {
			canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, drawRoomHandler);
			for (Node n : canvas.getChildren()) {
				if (n instanceof BoundsAnchor) {
					((BoundsAnchor) n).enableMouseEvents();
				}
			}
		}
	}

	public void disableRoomEditing() {
		if (canvas != null) {
			canvas.removeEventHandler(MouseEvent.MOUSE_CLICKED, drawRoomHandler);
			for (Node n : canvas.getChildren()) {
				if (n instanceof BoundsAnchor) {
					((BoundsAnchor) n).disableMouseEvents();
				}
			}
		}
	}

	public void fillOutputTab(TableView<ISolution> table, TitledPane detailsPane) {

		TableColumn<ISolution, Double> colEnergySavings = new TableColumn<ISolution, Double>(
				"Energy savings");
		colEnergySavings.setPrefWidth(179);
		colEnergySavings.setCellValueFactory(new PropertyValueFactory<>(
				"energySavings"));
		table.getColumns().add(colEnergySavings);
		TableColumn<ISolution, Double> colExploitationCostsSavings = new TableColumn<ISolution, Double>(
				"Exploitation costs savings");
		colExploitationCostsSavings.setPrefWidth(179);
		colExploitationCostsSavings
				.setCellValueFactory(new PropertyValueFactory<>(
						"exploitationCostsSavings"));
		table.getColumns().add(colExploitationCostsSavings);
		TableColumn<ISolution, Double> colOperatingCostsSavings = new TableColumn<ISolution, Double>(
				"Operating costs savings");
		colOperatingCostsSavings.setPrefWidth(179);
		colOperatingCostsSavings
				.setCellValueFactory(new PropertyValueFactory<>(
						"operatingCostsSavings"));
		table.getColumns().add(colOperatingCostsSavings);
		TableColumn<ISolution, Double> colSimplePaybackPeriod = new TableColumn<ISolution, Double>(
				"Simple payback period");
		colSimplePaybackPeriod.setPrefWidth(179);
		colSimplePaybackPeriod.setCellValueFactory(new PropertyValueFactory<>(
				"simplePaybackPeriod"));
		table.getColumns().add(colSimplePaybackPeriod);
		TableColumn<ISolution, Double> colNPV = new TableColumn<ISolution, Double>(
				"NPV");
		colNPV.setPrefWidth(179);
		colNPV.setCellValueFactory(new PropertyValueFactory<>("NPV"));
		table.getColumns().add(colNPV);

		Map<ILightPoint, ILightSource> m = new HashMap<ILightPoint, ILightSource>();
		m.put(new ILightPoint(new IPoint(1.0, 1.0), 1.0), new ILightSource("419R.IES", 50, 10));
		m.put(new ILightPoint(new IPoint(2.0, 2.0), 2.0), new ILightSource("12AB.IES", 20, 15));
		m.put(new ILightPoint(new IPoint(3.0, 3.0), 3.0), new ILightSource("992E.IES", 15, 20));
		for(double i = 1.0 ; i <= 10.0 ; i += 1.0) {
			table.getItems().add(new ISolution(i, i, i, i, i, m));
		}

		table.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ISolution>() {
					@Override
					public void changed(
							ObservableValue<? extends ISolution> observable,
							ISolution oldValue, ISolution newValue) {
						if (oldValue != null) {
							detailsPane.setVisible(false);
						}
						if (newValue != null) {
							fillOutputDetailsPane(detailsPane, newValue);
							detailsPane.setVisible(true);
						}
					}
				});
		
	}

	public void fillOutputDetailsPane(TitledPane detailsPane, ISolution solution) {
		VBox content = new VBox(15.0);
		content.getChildren().add(new Label("Selected light sources:"));
		TableView<SolutionDetailsRow> table = new TableView<SolutionDetailsRow>();
		ObservableList<SolutionDetailsRow> data = FXCollections
				.observableArrayList();
		for (Entry<ILightPoint, ILightSource> e : solution.getLightMap()
				.entrySet()) {
			data.add(new SolutionDetailsRow(e.getKey(), e.getValue()));
		}
		TableColumn<SolutionDetailsRow, String> colLightPoint = new TableColumn<SolutionDetailsRow, String>(
				"Light point");
		colLightPoint.setCellValueFactory(new PropertyValueFactory<>(
				"lightPoint"));
		table.getColumns().add(colLightPoint);
		TableColumn<SolutionDetailsRow, String> colLightSource = new TableColumn<SolutionDetailsRow, String>(
				"Light source");
		colLightSource.setCellValueFactory(new PropertyValueFactory<>(
				"lightSource"));
		table.getColumns().add(colLightSource);

		table.setItems(data);
		content.getChildren().add(table);
		detailsPane.setContent(content);
	}

	public double getOrgSceneX() {
		return orgSceneX;
	}

	public void setOrgSceneX(double orgSceneX) {
		this.orgSceneX = orgSceneX;
	}

	public double getOrgSceneY() {
		return orgSceneY;
	}

	public void setOrgSceneY(double orgSceneY) {
		this.orgSceneY = orgSceneY;
	}

	public double getOrgTranslateX() {
		return orgTranslateX;
	}

	public void setOrgTranslateX(double orgTranslateX) {
		this.orgTranslateX = orgTranslateX;
	}

	public double getOrgTranslateY() {
		return orgTranslateY;
	}

	public void setOrgTranslateY(double orgTranslateY) {
		this.orgTranslateY = orgTranslateY;
	}

	public ObservableList<Ies> getIesList() {
		return iesList;
	}

	public List<BoundsAnchor> getAnchorsList() {
		return anchorsList;
	}

}
