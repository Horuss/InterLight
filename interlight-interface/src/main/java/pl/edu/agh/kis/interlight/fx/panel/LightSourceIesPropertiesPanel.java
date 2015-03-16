package pl.edu.agh.kis.interlight.fx.panel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.Ies;
import pl.edu.agh.kis.interlight.fx.model.LightSource;

public class LightSourceIesPropertiesPanel extends LightSourcePropertiesPanel {

	protected ComboBox<Ies> cmbIes;
	protected Spinner<Integer> spinDimming;

	public LightSourceIesPropertiesPanel(LightSource lightSource,
			GuiHelper guiHelper) {
		super(lightSource, guiHelper);

		add(new Label("IES:"), 0, 4);
		HBox cmbPane = new HBox();
		cmbIes = new ComboBox<Ies>();
		cmbIes.setItems(guiHelper.getIesList());
		cmbIes.getSelectionModel().select(0);
		cmbIes.getStyleClass().add("iesCombo");
		cmbIes.setMaxWidth(105);
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
		add(cmbPane, 1, 4);

		add(new Label("Dimming [%]:"), 0, 5);
		spinDimming = new Spinner<Integer>(0, 100, 15, 1);
		add(spinDimming, 1, 5);
		spinDimming.setEditable(false);

	}
	
	@Override
	protected void createLightDeleteButton(LightSource lightSource) {
		createDeleteButton(lightSource, 6);
	}

}
