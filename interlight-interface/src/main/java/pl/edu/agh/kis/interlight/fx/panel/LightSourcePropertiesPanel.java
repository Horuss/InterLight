package pl.edu.agh.kis.interlight.fx.panel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.LightSource;
import pl.edu.agh.kis.interlight.fx.parts.LuminousIntensityPolarDiagram;

public class LightSourcePropertiesPanel extends GridPane {

	private GuiHelper guiHelper;

	private Button btnDelete;

	public LightSourcePropertiesPanel(LightSource lightSource,
			GuiHelper guiHelper) {
		this.guiHelper = guiHelper;

		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));

		add(new Label("Power: " + lightSource.getIes().getPower() + " W"), 0, 0);

		LuminousIntensityPolarDiagram luminousIntensityPolarDiagram = new LuminousIntensityPolarDiagram(
				lightSource.getIes());
		add(luminousIntensityPolarDiagram.getChartViewer(), 0, 1);
		
		btnDelete = new Button("Delete");
		btnDelete.setPrefWidth(Double.MAX_VALUE);
		btnDelete.setGraphic(new ImageView(new Image(getClass()
				.getClassLoader().getResourceAsStream("img/16delete.png"))));
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Files.delete(Paths.get(System.getProperty("user.dir")
							+ "/res/ies/" + lightSource.getIes().getName()));
					guiHelper.getSceneModel().getLightSources()
							.remove(lightSource);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		add(btnDelete, 0, 2, 2, 1);
	}

	public GuiHelper getGuiHelper() {
		return guiHelper;
	}

}
