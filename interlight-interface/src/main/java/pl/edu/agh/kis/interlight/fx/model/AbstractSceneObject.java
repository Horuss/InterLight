package pl.edu.agh.kis.interlight.fx.model;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.panel.AbstractPropertiesPanel;

public abstract class AbstractSceneObject {

	private final int id;
	private Double height;
	protected AbstractPropertiesPanel propertiesPanel;
	protected EventHandler<MouseEvent> mousePressedEventHandler;
	protected EventHandler<MouseEvent> mouseDraggedEventHandler;
	protected EventHandler<MouseEvent> mouseEnteredEventHandler;
	protected EventHandler<MouseEvent> mouseExitedEventHandler;

	public AbstractSceneObject() {
		this.id = getNextId();
	}

	public int getId() {
		return id;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public AbstractPropertiesPanel getPropertiesPanel() {
		return propertiesPanel;
	}

	public abstract Shape getSceneObject();

	public abstract void createPropertiesPanel(GuiHelper guiHelper);

	public void createEventHandlers(GuiHelper guiHelper, ListView<AbstractSceneObject> listView) {
		this.mousePressedEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				selectMe(listView);
				guiHelper.setOrgSceneX(event.getSceneX());
				guiHelper.setOrgSceneY(event.getSceneY());
				guiHelper.setOrgTranslateX(((Shape) (event.getSource()))
						.getLayoutX());
				guiHelper.setOrgTranslateY(((Shape) (event.getSource()))
						.getLayoutY());
				event.consume();
			}
		};
		this.mouseDraggedEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double offsetX = event.getSceneX() - guiHelper.getOrgSceneX();
				double offsetY = event.getSceneY() - guiHelper.getOrgSceneY();
				double newTranslateX = guiHelper.getOrgTranslateX() + offsetX;
				double newTranslateY = guiHelper.getOrgTranslateY() + offsetY;

				Shape source = (Shape) (event.getSource());

				if (canTranslateX(newTranslateX)) {
					source.setLayoutX(newTranslateX);
				}
				if (canTranslateY(newTranslateY)) {
					source.setLayoutY(newTranslateY);
				}

				propertiesPanel.updatePosition(source.getLayoutX()
						* GuiHelper.SCALE_PX_TO_M, source.getLayoutY()
						* GuiHelper.SCALE_PX_TO_M);

				event.consume();
			}
		};
		this.mouseEnteredEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				getSceneObject().getScene().setCursor(Cursor.MOVE);
				mouseEvent.consume();
			}
		};
		this.mouseExitedEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				getSceneObject().getScene().setCursor(Cursor.DEFAULT);
				mouseEvent.consume();
			}
		};
	}

	public void enableEventHandlers() {
		this.getSceneObject().addEventHandler(MouseEvent.MOUSE_PRESSED,
				mousePressedEventHandler);
		this.getSceneObject().addEventHandler(MouseEvent.MOUSE_DRAGGED,
				mouseDraggedEventHandler);
		this.getSceneObject().addEventHandler(MouseEvent.MOUSE_ENTERED,
				mouseEnteredEventHandler);
		this.getSceneObject().addEventHandler(MouseEvent.MOUSE_EXITED,
				mouseExitedEventHandler);
		this.getSceneObject().getStyleClass().add("activeLayer");
	}

	public void disableEventHandlers() {
		this.getSceneObject().removeEventHandler(MouseEvent.MOUSE_PRESSED,
				mousePressedEventHandler);
		this.getSceneObject().removeEventHandler(MouseEvent.MOUSE_DRAGGED,
				mouseDraggedEventHandler);
		this.getSceneObject().removeEventHandler(MouseEvent.MOUSE_ENTERED,
				mouseEnteredEventHandler);
		this.getSceneObject().removeEventHandler(MouseEvent.MOUSE_EXITED,
				mouseExitedEventHandler);
		this.getSceneObject().getStyleClass().remove("activeLayer");
	}

	protected void selectMe(ListView<AbstractSceneObject> listView) {
		listView.getSelectionModel().select(this);
	}

	public abstract int getNextId();

	protected abstract boolean canTranslateX(Double newTranslateX);

	protected abstract boolean canTranslateY(Double newTranslateY);

}
