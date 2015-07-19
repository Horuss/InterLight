package pl.edu.agh.kis.interlight.fx.parts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.LightPointNet;

public class LightPointNetDialog extends Dialog<LightPointNet> {

	private LightPointNet result;

	private Spinner<Integer> spnCountX;
	private Spinner<Integer> spnCountY;
	private Spinner<Double> spnGapX;
	private Spinner<Double> spnGapY;
	private Spinner<Double> spnMarginX;
	private Spinner<Double> spnMarginY;

	public LightPointNetDialog(Double width, Double length) {

		setTitle("InterLight - light points net");
		setHeaderText("Set the light points net parameters");
		getDialogPane().getStylesheets().add("/styles/style.css");

		getDialogPane().getButtonTypes().addAll(ButtonType.OK,
				ButtonType.CANCEL);

		final Button btnOk = (Button) getDialogPane().lookupButton(
				ButtonType.OK);
		btnOk.addEventFilter(ActionEvent.ACTION, event -> {
			if (!validateAndStore()) {
				event.consume();
			}
		});

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 20, 20, 20));

		grid.add(new Label("Count (horizontal):"), 0, 0);
		spnCountX = createIntegerSpinner();
		spnCountX.getStyleClass().add(
				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
		grid.add(spnCountX, 1, 0);
		grid.add(new Label("Count (vertical):"), 0, 1);
		spnCountY = createIntegerSpinner();
		grid.add(spnCountY, 1, 1);

		grid.add(new Label("Gap (horizontal) [m]:"), 0, 2);
		spnGapX = createDoubleSpinner(width);
		spnGapX.getStyleClass().add(
				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
		grid.add(spnGapX, 1, 2);
		grid.add(new Label("Gap (vertical) [m]:"), 0, 3);
		spnGapY = createDoubleSpinner(length);
		grid.add(spnGapY, 1, 3);

		grid.add(new Label("Margin (horizontal) [m]:"), 2, 2);
		spnMarginX = createDoubleSpinner(width);
		spnMarginX.getStyleClass().add(
				Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
		grid.add(spnMarginX, 3, 2);
		grid.add(new Label("Margin (vertical) [m]:"), 2, 3);
		spnMarginY = createDoubleSpinner(width);
		grid.add(spnMarginY, 3, 3);

		getDialogPane().setContent(grid);

		setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				return result;
			}
			return null;
		});
	}

	private boolean validateAndStore() {
		result = new LightPointNet();
		if (spnCountX.getPseudoClassStates().contains(GuiHelper.errorClass)) {
			return false;
		}
		result.setAmountX(spnCountX.getValue());
		if (spnCountY.getPseudoClassStates().contains(GuiHelper.errorClass)) {
			return false;
		}
		result.setAmountY(spnCountY.getValue());
		if (spnGapX.getPseudoClassStates().contains(GuiHelper.errorClass)) {
			return false;
		}
		result.setSpacingX(spnGapX.getValue());
		if (spnGapY.getPseudoClassStates().contains(GuiHelper.errorClass)) {
			return false;
		}
		result.setSpacingY(spnGapY.getValue());
		if (spnMarginX.getPseudoClassStates().contains(GuiHelper.errorClass)) {
			return false;
		}
		result.setMarginX(spnMarginX.getValue());
		if (spnMarginY.getPseudoClassStates().contains(GuiHelper.errorClass)) {
			return false;
		}
		result.setMarginY(spnMarginY.getValue());
		return true;
	}

	private Spinner<Integer> createIntegerSpinner() {
		Spinner<Integer> spinner = new Spinner<>(1, 100, 2, 1);
		spinner.setEditable(true);
		spinner.setPrefWidth(100);
		spinner.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinner.pseudoClassStateChanged(GuiHelper.errorClass,
								false);
						try {
							Integer value = Integer.parseInt(newValue);
							spinner.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinner.pseudoClassStateChanged(
									GuiHelper.errorClass, true);
						}
					}
				});

		return spinner;
	}

	private Spinner<Double> createDoubleSpinner(double axisSize) {
		Spinner<Double> spinner = new Spinner<>(0, axisSize, 0.5, 0.5);
		spinner.setEditable(true);
		spinner.setPrefWidth(100);
		spinner.getEditor().textProperty()
				.addListener(new ChangeListener<String>() {
					@Override
					public void changed(
							ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
						spinner.pseudoClassStateChanged(GuiHelper.errorClass,
								false);
						try {
							Double value = Double.parseDouble(newValue);
							spinner.getValueFactory().setValue(value);
						} catch (NumberFormatException nfe) {
							spinner.pseudoClassStateChanged(
									GuiHelper.errorClass, true);
						}
					}
				});

		return spinner;
	}

}
