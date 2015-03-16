package pl.edu.agh.kis.interlight.fx.parts;

import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import pl.edu.agh.kis.interlight.fx.GuiHelper;

public class BoundsAnchor extends Circle {

	private Double dragDeltaX;
	private Double dragDeltaY;
	private DoubleProperty x;
	private DoubleProperty y;

	private EventHandler<MouseEvent> mouseEventAny;
	private EventHandler<MouseEvent> mouseEventPressed;
	private EventHandler<MouseEvent> mouseEventDragged;
	private EventHandler<MouseEvent> mouseEventEntered;
	private EventHandler<MouseEvent> mouseEventExited;

	public BoundsAnchor(DoubleProperty x, DoubleProperty y) {
		super(x.get(), y.get(), 5);
		this.x = x;
		this.y = y;
		setFill(Color.GREEN.deriveColor(1, 1, 1, 0.5));
		setStroke(Color.GREEN);
		setStrokeWidth(1);
		setStrokeType(StrokeType.OUTSIDE);
		this.x.bind(centerXProperty());
		this.y.bind(centerYProperty());
		mouseEventAny = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				mouseEvent.consume();
			}
		};
		mouseEventPressed = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				dragDeltaX = getCenterX() - mouseEvent.getX();
				dragDeltaY = getCenterY() - mouseEvent.getY();
				getScene().setCursor(Cursor.MOVE);
				mouseEvent.consume();
			}
		};
		mouseEventDragged = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				double newX = mouseEvent.getX() + dragDeltaX;
				if (newX >= 0 && newX <= GuiHelper.CANVAS_WIDTH) {
					setCenterX(newX);
				}
				double newY = mouseEvent.getY() + dragDeltaY;
				if (newY >= 0 && newY <= GuiHelper.CANVAS_HEIGHT) {
					setCenterY(newY);
				}
				mouseEvent.consume();
			}
		};
		mouseEventEntered = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				getScene().setCursor(Cursor.MOVE);
				mouseEvent.consume();
			}
		};
		mouseEventExited = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				getScene().setCursor(Cursor.DEFAULT);
				mouseEvent.consume();
			}
		};
		enableMouseEvents();
	}

	public void enableMouseEvents() {
		addEventHandler(MouseEvent.ANY, mouseEventAny);
		addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEventPressed);
		addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEventDragged);
		addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEventEntered);
		addEventHandler(MouseEvent.MOUSE_EXITED, mouseEventExited);
	}

	public void disableMouseEvents() {
		removeEventHandler(MouseEvent.ANY, mouseEventAny);
		removeEventHandler(MouseEvent.MOUSE_PRESSED, mouseEventPressed);
		removeEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEventDragged);
		removeEventHandler(MouseEvent.MOUSE_ENTERED, mouseEventEntered);
		removeEventHandler(MouseEvent.MOUSE_EXITED, mouseEventExited);
	}

}
