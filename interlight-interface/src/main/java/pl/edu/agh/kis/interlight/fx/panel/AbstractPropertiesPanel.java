package pl.edu.agh.kis.interlight.fx.panel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.AbstractSceneObject;
import pl.edu.agh.kis.interlight.fx.model.SceneShape;

public abstract class AbstractPropertiesPanel extends GridPane {

	protected static final Logger logger = LogManager
			.getLogger(AbstractPropertiesPanel.class.getName());
	protected final PseudoClass errorClass = PseudoClass
			.getPseudoClass("error");

	protected Spinner<Double> spinHeight;
	protected Button btnDelete;
	protected CheckBox chkWorkspace;
	
	protected GuiHelper guiHelper;

	public AbstractPropertiesPanel(AbstractSceneObject object,
			GuiHelper guiHelper) {
		this.guiHelper = guiHelper;

		setAlignment(Pos.CENTER);
		setHgap(10);
		setVgap(10);
		setPadding(new Insets(10, 10, 10, 10));
		
		Label lblHeight = new Label("Height [m]:");
		add(lblHeight, 0, 0);
		spinHeight = new Spinner<Double>(0.0, 10.0, object.getHeight(), 0.5);
		add(spinHeight, 1, 0);
		spinHeight.setEditable(true);
		spinHeight.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinHeight.pseudoClassStateChanged(errorClass, false);
						try {
							Double value = Double.parseDouble(newValue);
							object.setHeight(value);
							spinHeight.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinHeight.pseudoClassStateChanged(errorClass, true);
						}
					}
				});

	}

	public abstract void updatePosition(Double x, Double y);
	
	public void createDeleteButton(AbstractSceneObject object, int gridIndex) {
		btnDelete = new Button("Delete");
		btnDelete.setPrefWidth(Double.MAX_VALUE);
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				guiHelper.getSceneModel().getShapes().remove(object);
				guiHelper.getCanvas().getChildren()
						.remove(object.getSceneObject());

			}
		});
		add(btnDelete, 0, gridIndex, 2, 1);
	}
	
	public void createWorkspaceCheckbox(SceneShape object, int gridIndex) {
		Label lbl = new Label("Is workspace?");
		add(lbl, 0, gridIndex);
		chkWorkspace = new CheckBox();
		chkWorkspace.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov,
							Boolean oldVal, Boolean newVal) {
						object.setWorkspace(newVal);
					}
				});
		add(chkWorkspace, 1, gridIndex);
	}

}
