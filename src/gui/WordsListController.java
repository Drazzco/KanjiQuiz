package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Words;
import model.services.WordsService;

public class WordsListController implements Initializable, DataChangeListener {

	private WordsService service;
	
	@FXML
	private TableView<Words> tableViewWords;
	@FXML
	private TableColumn<Words, Integer> tableColumnId;
	@FXML
	private TableColumn<Words, Integer> tableColumnLevel;
	@FXML
	private TableColumn<Words, Integer> tableColumnLesson;
	@FXML
	private TableColumn<Words, String> tableColumnEng;
	@FXML
	private TableColumn<Words, Words> tableColumnEDIT;
	@FXML
	private TableColumn<Words, Words> tableColumnREMOVE;
	@FXML
	private Button btNew;
	
	private ObservableList<Words> obsList;
	
	@FXML
	public void onBtNewAction(ActionEvent event)
	{
		Stage parentStage = Utils.currentStage(event);
		Words obj = new Words();
		createDialogForm(obj, "/gui/WordsForm.fxml", parentStage);
	}
	
	public void setWordsService(WordsService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
		tableColumnLesson.setCellValueFactory(new PropertyValueFactory<>("lesson"));
		tableColumnEng.setCellValueFactory(new PropertyValueFactory<>("englishTrad"));
		
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewWords.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView()
	{
		if(service == null)
		{
			throw new IllegalStateException("Service was null");
		}
		List<Words> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewWords.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}
	
	private void createDialogForm(Words obj, String absoluteName, Stage parentStage)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			WordsFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.setDepartmentService(new WordsService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter word data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}
	
	private void initEditButtons()
	{
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Words, Words>()
				{
					private final Button button = new Button("Edit");
					
					@Override
					protected void updateItem(Words obj, boolean empty)
					{
						super.updateItem(obj, empty);
						if(obj == null)
						{
							setGraphic(null);
							return;
						}
						
						setGraphic(button);
						button.setOnAction(event -> createDialogForm(obj, "/gui/WordsForm.fxml", Utils.currentStage(event)));
					}
				});
	}
	
	private void initRemoveButtons()
	{
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Words, Words>()
				{
					private final Button button = new Button("Remove");
					
					@Override
					protected void updateItem(Words obj, boolean empty)
					{
						super.updateItem(obj, empty);
						if(obj == null)
						{
							setGraphic(null);
							return;
						}
						
						setGraphic(button);
						button.setOnAction(event -> removeEntity(obj));
					}
				});
	}

	private void removeEntity(Words obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");
		if(result.get() == ButtonType.OK)
		{
			if(service == null)
			{
				throw new IllegalStateException("Service was null");
			}
			try
			{
				service.remove(obj);
				updateTableView();
			}
			catch(DbIntegrityException e)
			{
				Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
}
