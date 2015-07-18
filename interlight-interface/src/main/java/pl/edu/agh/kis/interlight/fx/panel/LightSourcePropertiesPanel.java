package pl.edu.agh.kis.interlight.fx.panel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.Ies;
import pl.edu.agh.kis.interlight.fx.model.LightSource;

public class LightSourcePropertiesPanel extends GridPane {

	private static final Logger logger = LogManager
			.getLogger(LightSourcePropertiesPanel.class.getName());
	private final PseudoClass errorClass = PseudoClass
			.getPseudoClass("error");
	
	private GuiHelper guiHelper;
	
	private Spinner<Double> spinPower;
	private ComboBox<Ies> cmbIes;
	private Spinner<Integer> spinDimming;
	private Button btnDelete;

	public LightSourcePropertiesPanel(LightSource lightSource,
			GuiHelper guiHelper) {
		this.guiHelper = guiHelper;
		
		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));

		add(new Label("Power [W]:"), 0, 0);
		spinPower = new Spinner<Double>(0.0, 1000.0, 40.0, 5.0);
		add(spinPower, 1, 0);
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
		
		add(new Label("IES:"), 0, 1);
		HBox cmbPane = new HBox();
		cmbIes = new ComboBox<Ies>();
		cmbIes.setItems(guiHelper.getIesList());
		cmbIes.getSelectionModel().select(0);
		cmbIes.getStyleClass().add("iesCombo");
		cmbIes.setMaxWidth(105);
		cmbIes.setPrefWidth(105);
		Button addButton = new Button("+");
		addButton.setPrefWidth(40);
		addButton
				.setOnAction((ActionEvent e) -> {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Add IES file...");
					fileChooser.setInitialDirectory(new File(System
							.getProperty("user.home")));
					fileChooser.getExtensionFilters()
							.add(new FileChooser.ExtensionFilter("IES file",
									"*.ies"));
					File iesFile = fileChooser.showOpenDialog(this.getScene()
							.getWindow());
					try {
						String fileName = iesFile.getName();
						if (Files.exists(Paths.get(System
								.getProperty("user.dir")
								+ "/res/ies/"
								+ iesFile.getName()))) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("IES file error");
							alert.setHeaderText("IES file name error");
							alert.setContentText("There is already an IES file with that name in InterLight resources");
							alert.showAndWait();
						} else {
							Path copy = Files.copy(
									iesFile.toPath(),
									Paths.get(System.getProperty("user.dir")
											+ "/res/ies/" + fileName));
							Ies ies = new Ies(copy);
							cmbIes.getItems().add(ies);
							cmbIes.getSelectionModel().select(ies);
						}
					} catch (Exception ex) {
						logger.error("Error copying new IES file");
					}
				});
		cmbPane.getChildren().add(cmbIes);
		cmbPane.getChildren().add(addButton);
		add(cmbPane, 1, 1);

		add(new Label("Dimming [%]:"), 0, 2);
		spinDimming = new Spinner<Integer>(0, 100, 15, 1);
		add(spinDimming, 1, 2);
		spinDimming.setEditable(false);

		btnDelete = new Button("Delete");
		btnDelete.setPrefWidth(Double.MAX_VALUE);
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				guiHelper.getSceneModel().getLightSources().remove(lightSource);
			}
		});
		add(btnDelete, 0, 3, 2, 1);
	}

	public GuiHelper getGuiHelper() {
		return guiHelper;
	}

}
