package pl.edu.agh.kis.interlight.fx.panel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.Room;

public class RoomPropertiesPanel extends AbstractPropertiesPanel {
	
	private Button btnReset;
	private CheckBox chkAlign;
	private Button btnFit;

	public RoomPropertiesPanel(Room room, GuiHelper guiHelper) {
		super(room, guiHelper);
		
		add(new Label("Align:"), 0, 1);
		chkAlign = new CheckBox();
		chkAlign.setSelected(true);
		add(chkAlign, 1, 1);
		
		btnFit = new Button("Draw fitted to scene");
		btnFit.setPrefWidth(Double.MAX_VALUE);
		btnFit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				guiHelper.addRoomPoint(0., 0.);
				guiHelper.addRoomPoint(GuiHelper.CANVAS_WIDTH, 0.);
				guiHelper.addRoomPoint(GuiHelper.CANVAS_WIDTH, GuiHelper.CANVAS_HEIGHT);
				guiHelper.addRoomPoint(0., GuiHelper.CANVAS_HEIGHT);
				btnFit.setDisable(true);
			}
		});
		btnFit.setGraphic(new ImageView(new Image(getClass()
				.getClassLoader().getResourceAsStream("img/16arrow_out.png"))));
		add(btnFit, 0, 2, 2, 1);
		
		btnReset = new Button("Reset room");
		btnReset.setPrefWidth(Double.MAX_VALUE);
		btnReset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				guiHelper.getSceneModel().createBounds(guiHelper.getCanvas());
				guiHelper.getAnchorsList().clear();
				btnFit.setDisable(false);
				guiHelper.setHintMessage(GuiHelper.HINT_ROOM_INIT);
			}
		});
		btnReset.setGraphic(new ImageView(new Image(getClass()
				.getClassLoader().getResourceAsStream("img/16delete.png"))));
		add(btnReset, 0, 3, 2, 1);
		
	}

	@Override
	public void updatePosition(Double x, Double y) {
		return;
	}

	public CheckBox getChkAlign() {
		return chkAlign;
	}

	public Button getBtnFit() {
		return btnFit;
	}

}
