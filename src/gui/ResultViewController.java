package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.WordsService;

public class ResultViewController implements Initializable {
	
	@FXML
	private Label userName;
	@FXML
	private Label level;
	@FXML
	private Label lesson;
	@FXML
	private Label difLevel;
	@FXML
	private Label points;
	@FXML
	private Label pct;
	@FXML
	private Button anotherLevel;
	@FXML
	private Button anotherLesson;
	
	@FXML
	public void onAnotherLevelAction()
	{
		Main.sequence = 0;
		Main.user.setPoints(0);
		loadView("/gui/LevelView2.fxml");
	}
	
	@FXML
	public void onAnotherLessonAction()
	{
		Main.sequence = 0;
		Main.user.setPoints(0);
		loadView("/gui/LessonView.fxml", (LessonViewController controller) -> {
			controller.setWordsService(new WordsService());
		});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		userName.setText(Main.user.getName());
		points.setText(Main.user.getPoints() + " de " + Main.questions.size());
		difLevel.setText(getDifLevel());
		level.setText(String.valueOf(Main.level));
		lesson.setText(String.valueOf(Main.lesson));
		int prct = (int)((Main.user.getPoints()*100)/Main.questions.size());
		pct.setText(prct + "%");
	}
	
	private String getDifLevel()
	{
		switch(Main.difLevel)
		{
		case 1:
			return "Easy";
		case 2:
			return "Normal";
		case 3:
			return "Hard";
		default:
			return "Very Hard";
		}
	}

	private void loadView(String absoluteName) //Para nao ser interrompido durante mudanca de thread (synchronized)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		}
		catch(IOException e)
		{
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) //Para nao ser interrompido durante mudanca de thread (synchronized)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
		}
		catch(IOException e)
		{
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
