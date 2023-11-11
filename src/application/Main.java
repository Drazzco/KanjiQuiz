package application;
	
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import model.entities.Question;
import model.entities.User;


public class Main extends Application {
	
	private static Scene mainScene;
	public static User user = new User();
	public static Integer level;
	public static Integer lesson;
	public static Integer difLevel;
	public static Boolean kanji;
	public static int sequence = 0;
	public static List<Question> questions;
	
	@Override
	public void start(Stage primaryStage) {
		System.setProperty("file.encoding", "UTF-8");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LevelView.fxml"));
			ScrollPane scrollPane = loader.load();
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			mainScene = new Scene(scrollPane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Kanji Study - Sou Matome");
			primaryStage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene()
	{
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
