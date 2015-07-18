package pl.edu.agh.kis.interlight.fx.parts;

import java.awt.geom.Point2D;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pl.edu.agh.kis.interlight.fx.GuiHelper;

public class BoundsAnchor extends Circle {
	
	private GuiHelper guiHelper;

	private Double dragDeltaX;
	private Double dragDeltaY;
	private DoubleProperty x;
	private DoubleProperty y;

	private EventHandler<MouseEvent> mouseEventAny;
	private EventHandler<MouseEvent> mouseEventPressed;
	private EventHandler<MouseEvent> mouseEventDragged;
	private EventHandler<MouseEvent> mouseEventEntered;
	private EventHandler<MouseEvent> mouseEventExited;
	
	private Label label;
	private Label lengthLabelPrev;
	private Label lengthLabelNext;

	public BoundsAnchor(GuiHelper gh, double x, double y) {
		super();
		this.guiHelper = gh;
		this.label = new Label();
		gh.getCanvas().getChildren().add(label);
		lengthLabelNext = new Label();
		lengthLabelNext.setTextFill(Color.BLACK);
		gh.getCanvas().getChildren().add(lengthLabelNext);
		if(!gh.getAnchorsList().isEmpty()) {
			lengthLabelPrev = gh.getAnchorsList().get(gh.getAnchorsList().size() - 1).getLengthLabelNext();
			gh.getAnchorsList().get(0).setLengthLabelPrev(lengthLabelNext);
		}
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.x.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number oldX, Number x) {
				gh.getSceneModel().getRoomBounds().getPoints()
						.set(guiHelper.getAnchorsList().indexOf(BoundsAnchor.this) * 2, (double) x);
			}
		});
		this.y.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number oldY, Number y) {
				gh.getSceneModel().getRoomBounds().getPoints()
						.set(guiHelper.getAnchorsList().indexOf(BoundsAnchor.this) * 2 + 1, (double) y);
			}
		});
		
		setCenterX(this.x.get());
		setCenterY(this.y.get());
		setRadius(5);
		
		label.setTextFill(Color.GREEN);
		label.setFont(Font.font(label.getFont().getFamily(), FontWeight.BOLD, label.getFont().getSize()));
		setFill(Color.GREEN.deriveColor(1, 1, 1, 0.5));
		setStroke(Color.GREEN);
		setStrokeWidth(2);
		setStrokeType(StrokeType.OUTSIDE);
		
		if(!gh.getAnchorsList().isEmpty()) {
			BoundsAnchor last = gh.getAnchorsList().get(gh.getAnchorsList().size() - 1);
			last.getLabel().setFont(Font.font(last.getLabel().getFont().getFamily(), FontWeight.NORMAL, last.getLabel().getFont().getSize()));
			last.setStrokeWidth(1);
		}
		
		updateLabel(this.x.get(), this.y.get());
		if(gh.getAnchorsList().size() > 0) {
			updateLengthLabel(lengthLabelPrev, this.x.get(), this.y.get(), gh.getAnchorsList().get(gh.getAnchorsList().size() - 1).getCenterX(), gh.getAnchorsList().get(gh.getAnchorsList().size() - 1).getCenterY());
		} 
		if(gh.getAnchorsList().size() > 1) {
			updateLengthLabel(lengthLabelNext, this.x.get(), this.y.get(), gh.getAnchorsList().get(0).getCenterX(), gh.getAnchorsList().get(0).getCenterY());
		}
		
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
				if(mouseEvent.getButton() ==  MouseButton.SECONDARY) {
					guiHelper.removeAnchor(BoundsAnchor.this);
				} else if(mouseEvent.getButton() ==  MouseButton.PRIMARY) {
					dragDeltaX = getCenterX() - mouseEvent.getX();
					dragDeltaY = getCenterY() - mouseEvent.getY();
					getScene().setCursor(Cursor.MOVE);
				}
				mouseEvent.consume();
			}
		};
		mouseEventDragged = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if(mouseEvent.getButton() ==  MouseButton.PRIMARY) {
					double newX = mouseEvent.getX() + dragDeltaX;
					if (newX < 0) {
						newX = 0;
					} else if (newX > GuiHelper.CANVAS_WIDTH) {
						newX = GuiHelper.CANVAS_WIDTH;
					}
					double newY = mouseEvent.getY() + dragDeltaY;
					if (newY < 0) {
						newY = 0;
					} else if (newY > GuiHelper.CANVAS_HEIGHT) {
						newY = GuiHelper.CANVAS_HEIGHT;
					}
					setCenterX(newX);
					setCenterY(newY);
					updateLabel(newX, newY);
					if(gh.getAnchorsList().size() > 0) {
						if(gh.getAnchorsList().indexOf(BoundsAnchor.this) == 0) {
							if(gh.getAnchorsList().size() != 1) {
								updateLengthLabel(lengthLabelPrev, newX, newY, gh.getAnchorsList().get(gh.getAnchorsList().size() - 1).getCenterX(), gh.getAnchorsList().get(gh.getAnchorsList().size() - 1).getCenterY());
							}
						} else {
							updateLengthLabel(lengthLabelPrev, newX, newY, gh.getAnchorsList().get(gh.getAnchorsList().indexOf(BoundsAnchor.this) - 1).getCenterX(), gh.getAnchorsList().get(gh.getAnchorsList().indexOf(BoundsAnchor.this) - 1).getCenterY());
						}
					} 
					if(gh.getAnchorsList().size() > 1) {
						if(gh.getAnchorsList().indexOf(BoundsAnchor.this) == (gh.getAnchorsList().size() - 1)) {
							updateLengthLabel(lengthLabelNext, newX, newY, gh.getAnchorsList().get(0).getCenterX(), gh.getAnchorsList().get(0).getCenterY());
						} else {
							updateLengthLabel(lengthLabelNext, newX, newY, gh.getAnchorsList().get(gh.getAnchorsList().indexOf(BoundsAnchor.this) + 1).getCenterX(), gh.getAnchorsList().get(gh.getAnchorsList().indexOf(BoundsAnchor.this) + 1).getCenterY());
						}
					}
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
	
	private void updateLabel(double x, double y) {
		label.setLayoutX(x - 25);
		label.setLayoutY(y - 25);
		label.setText(GuiHelper.DF.format(x * GuiHelper.SCALE_PX_TO_M) + ", "
				+ GuiHelper.DF.format(y * GuiHelper.SCALE_PX_TO_M));
	}
	
	private void updateLengthLabel(Label label, double x, double y, double x2,
			double y2) {
		label.setLayoutX((x + x2) / 2 - 10);
		label.setLayoutY((y + y2) / 2);
		label.setText(GuiHelper.DF.format(Point2D.distance(x, y, x2, y2) * GuiHelper.SCALE_PX_TO_M));
	}
	
	public Label getLabel() {
		return label;
	}

	public Label getLengthLabelNext() {
		return lengthLabelNext;
	}
	
	public void setLengthLabelNext(Label lengthLabelNext) {
		this.lengthLabelNext = lengthLabelNext;
	}
	
	public Label getLengthLabelPrev() {
		return lengthLabelPrev;
	}

	public void setLengthLabelPrev(Label lengthLabelPrev) {
		this.lengthLabelPrev = lengthLabelPrev;
	}

	public void updateDuringRemove() {
		if(guiHelper.getAnchorsList().size() > 1 && guiHelper.getAnchorsList().indexOf(this) == guiHelper.getAnchorsList().size() - 1) {
			BoundsAnchor boundsAnchor = guiHelper.getAnchorsList().get(guiHelper.getAnchorsList().size() - 2);
			boundsAnchor.setStrokeWidth(2);
			boundsAnchor.getLabel().setFont(Font.font(label.getFont().getFamily(), FontWeight.BOLD, label.getFont().getSize()));
		}
		
		if(guiHelper.getAnchorsList().size() > 2) {
			BoundsAnchor prev = null;
			BoundsAnchor next = null;
			if(guiHelper.getAnchorsList().indexOf(this) == 0) {
				prev = guiHelper.getAnchorsList().get(guiHelper.getAnchorsList().size() - 1);
			} else {
				prev = guiHelper.getAnchorsList().get(guiHelper.getAnchorsList().indexOf(this) - 1);
			}
			if(guiHelper.getAnchorsList().indexOf(this) == guiHelper.getAnchorsList().size() - 1) {
				next = guiHelper.getAnchorsList().get(0);
			} else {
				next = guiHelper.getAnchorsList().get(guiHelper.getAnchorsList().indexOf(this) + 1);
			}
			next.setLengthLabelPrev(lengthLabelPrev);
			updateLengthLabel(lengthLabelPrev, prev.getCenterX(), prev.getCenterY(), next.getCenterX(), next.getCenterY());
		}
	}

}
