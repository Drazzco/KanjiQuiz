package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.services.WordsService;

public class LevelViewController implements Initializable {

	@FXML
	private MenuItem menuItemWords;
	@FXML
	private ComboBox<String> comboBoxLevels;
	@FXML
	private Label userName;
	
	private ObservableList<String> obsList;
	
	@FXML
	public void onMenuItemWordsAction()
	{
		loadView("/gui/WordsList.fxml", (WordsListController controller) -> {
			controller.setWordsService(new WordsService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onComboBoxLevelsAction()
	{
		String level = comboBoxLevels.getSelectionModel().getSelectedItem();
		Main.level = levelToInt(level);
		loadView("/gui/LessonView.fxml", (LessonViewController controller) -> {
			controller.setWordsService(new WordsService());
		});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		userName.setText("Olá, " + Main.user.getName());
		List<String> list = new ArrayList<>();
		list.add("N1");
		list.add("N2");
		list.add("N3");
		obsList = FXCollections.observableArrayList(list);
		comboBoxLevels.setItems(obsList);

		Callback<ListView<String>, ListCell<String>> factory = lv -> new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty)
			{
				super.updateItem(item, empty);
				setText(empty ? "" : item);
			}
		};
		
		comboBoxLevels.setCellFactory(factory);
		comboBoxLevels.setButtonCell(factory.call(null));
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
	
	private Integer levelToInt(String level)
	{
		switch(level)
		{
		case "N1":
			return 1;
		case "N2":
			return 2;
		default:
			return 3;
		}
	}
}
