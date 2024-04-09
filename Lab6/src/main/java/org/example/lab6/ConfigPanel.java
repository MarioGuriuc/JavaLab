package org.example.lab6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ConfigPanel
{
	@FXML
	private TextField rowText;
	@FXML
	private TextField colText;
	@FXML
	private AnchorPane gridPane;
	@FXML
	private AnchorPane configPane;
	@FXML
	private Button createButton;
	@FXML
	private Button exportImageButton;

	@FXML
	public void createButtonAction(ActionEvent event)
	{
		int rows = Integer.parseInt(rowText.getText());
		int columns = Integer.parseInt(colText.getText());

		DrawingPanel.createMatrix(gridPane, rows, columns);
	}

	@FXML
	public void exportImageButtonAction(ActionEvent event)
	{
		DrawingPanel.exportImage(gridPane, "game.png");
	}
}
