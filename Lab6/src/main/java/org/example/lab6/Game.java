package org.example.lab6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application
{
	static final Color PLAYER_1_COLOR = Color.RED;
	static final Color PLAYER_2_COLOR = Color.BLUE;
	static int turn = 0;

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
