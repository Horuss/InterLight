package pl.edu.agh.kis.interlight.fx.parts;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import pl.edu.agh.kis.interlight.schema.JSONSchema;

public class EditJsonDialog extends Dialog<String> {

	public EditJsonDialog(String json) {
		getDialogPane().setPrefWidth(800.0);
		getDialogPane().setPrefHeight(600.0);
		setResizable(true);
		setTitle("InterLight - edit JSON");
		getDialogPane().getStylesheets().add("/styles/style.css");

		SplitPane splitPane = new SplitPane();

		VBox textAreaVBox = new VBox();
		textAreaVBox.setPadding(new Insets(10, 10, 10, 10));
		TextArea textArea = new TextArea(json);
		textArea.setPrefHeight(1000);
		textArea.setWrapText(true);
		textAreaVBox.getChildren().add(textArea);

		VBox exampleVBox = new VBox();
		exampleVBox.setSpacing(10);
		exampleVBox.setPadding(new Insets(10, 10, 10, 10));
		TextArea example = new TextArea("{\r\n"
				+ "  \"Name:\": \"Scene name\",\r\n"
				+ "  \"Scene width:\": 1.0,\r\n"
				+ "  \"Scene length:\": 2.0,\r\n"
				+ "  \"Room height:\": 3.0,\r\n" + "  \"Room points:\": [\r\n"
				+ "    {\r\n" + "      \"X\": 1.0,\r\n"
				+ "      \"Y\": 1.0\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"X\": 2.0,\r\n" + "      \"Y\": 2.0\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"Cuboids:\": [\r\n"
				+ "    {\r\n" + "      \"Point Base Top Left X\": 1.0,\r\n"
				+ "	  \"Point Base Top Left Y\": 2.0,\r\n"
				+ "      \"Height\": 3.0,\r\n" + "      \"Width\": 4.0,\r\n"
				+ "      \"Length\": 5.0,\r\n" + "      \"Rotation\": 6,\r\n"
				+ "      \"Workspace\": true\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"Cylinders:\": [\r\n" + "    {\r\n"
				+ "      \"Point Base Center X\": 1.0,\r\n"
				+ "      \"Point Base Center Y\": 2.0,\r\n"
				+ "      \"Radius Y\": 3.0,\r\n"
				+ "      \"Radius X\": 4.0,\r\n" + "      \"Height\": 5.0,\r\n"
				+ "      \"Rotation\": 6,\r\n"
				+ "      \"Workspace\": true\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"Light Points:\": [\r\n" + "    {\r\n"
				+ "      \"ID\": 1,\r\n" + "	  \"X\": 1.0,\r\n"
				+ "      \"Y\": 2.0,\r\n" + "      \"Height\": 3.0\r\n"
				+ "    }\r\n" + "  ],\r\n" + "  \"Light Sources:\": [\r\n"
				+ "    {\r\n" + "      \"IES\": \"Ies\",\r\n"
				+ "      \"Power\": 1.0\r\n" + "    }\r\n" + "  ]\r\n" + "}");
		example.setEditable(false);
		example.setPrefHeight(1000);
		example.getStyleClass().add("text-area-disabled");
		exampleVBox.getChildren().add(new Label("Example:"));
		exampleVBox.getChildren().add(example);

		splitPane.getItems().addAll(textAreaVBox, exampleVBox);
		splitPane.setPadding(new Insets(20, 20, 20, 20));
		splitPane.setDividerPositions(0.6, 0.4);

		getDialogPane().setContent(splitPane);

		getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.YES,
				ButtonType.CANCEL);

		final Button btnValidate = (Button) getDialogPane().lookupButton(
				ButtonType.YES);
		btnValidate.setText("Validate");
		btnValidate.addEventFilter(ActionEvent.ACTION, event -> {
			if (validate(textArea.getText())) {
				showValidMsg();
			} else {
				showInvalidMsg();
			}
			event.consume();
		});

		final Button btnSave = (Button) getDialogPane().lookupButton(
				ButtonType.OK);
		btnSave.setText("Validate & Confirm");
		btnSave.addEventFilter(ActionEvent.ACTION, event -> {
			if (!validate(textArea.getText())) {
				showInvalidMsg();
				event.consume();
			}
		});

		setResultConverter(dialogButton -> {
			if (dialogButton == ButtonType.OK) {
				if (validate(textArea.getText())) {
					return textArea.getText();
				}
			}
			return null;
		});
	}

	private void showInvalidMsg() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("InterLight");
		alert.setHeaderText("Invalid JSON");
		alert.setContentText("JSON scene is not valid in terms of schema");
		alert.showAndWait();
	}

	private void showValidMsg() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("InterLight");
		alert.setHeaderText("Valid JSON!");
		alert.setContentText("OK! JSON scene valid!");
		alert.showAndWait();
	}

	private boolean validate(String text) {
		try {
			return JSONSchema.validate(text);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
