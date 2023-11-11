package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Words;
import model.exceptions.ValidationException;
import model.services.WordsService;

public class WordsFormController implements Initializable{

	private Words entity;
	
	private WordsService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtLevel;
	@FXML
	private TextField txtLesson;
	@FXML
	private TextField txtHWQ;
	@FXML
	private TextField txtHW1;
	@FXML
	private TextField txtHW2;
	@FXML
	private TextField txtHW3;
	@FXML
	private TextField txtHW4;
	@FXML
	private TextField txtHWA;
	@FXML
	private TextField txtKWQ;
	@FXML
	private TextField txtKW1;
	@FXML
	private TextField txtKW2;
	@FXML
	private TextField txtKW3;
	@FXML
	private TextField txtKW4;
	@FXML
	private TextField txtKWA;
	@FXML
	private TextField txtEng;
	@FXML
	private TextField txtPt;
	@FXML
	private Label labelErrorHWQ;
	@FXML
	private Label labelErrorHW1;
	@FXML
	private Label labelErrorHW2;
	@FXML
	private Label labelErrorHW3;
	@FXML
	private Label labelErrorHW4;
	@FXML
	private Label labelErrorKWQ;
	@FXML
	private Label labelErrorKW1;
	@FXML
	private Label labelErrorKW2;
	@FXML
	private Label labelErrorKW3;
	@FXML
	private Label labelErrorKW4;
	@FXML
	private Label labelErrorEng;
	@FXML
	private Label labelErrorPt;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	
	@FXML
	public void onBtSaveAction(ActionEvent event)
	{
		if(entity == null)
		{
			throw new IllegalStateException("Entity was null");
		}
		if(service == null)
		{
			throw new IllegalStateException("Service was null");
		}
		try
		{
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
		}
		catch(DbException e)
		{
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		catch(ValidationException e)
		{
			setErrorMessages(e.getErrors());
		}
	}

	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners)
		{
			listener.onDataChanged();
		}
		
	}

	private Words getFormData() {
		Words obj = new Words();
		ValidationException exception = new ValidationException("Validation error");
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setLevel(Utils.tryParseToInt(txtLevel.getText()));
		obj.setLesson(Utils.tryParseToInt(txtLesson.getText()));
		obj.sethWordAnswer(Utils.tryParseToInt(txtHWA.getText()));
		obj.setkWordAnswer(Utils.tryParseToInt(txtKWA.getText()));
		if(txtHWQ.getText() == null || txtHWQ.getText().trim().equals(""))
		{
			exception.addError("HWQ", "Field can't be empty");
		}
		obj.sethWordQuestion(txtHWQ.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtHW1.getText() == null || txtHW1.getText().trim().equals(""))
		{
			exception.addError("HW1", "Field can't be empty");
		}
		obj.sethWord1(txtHW1.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtHW2.getText() == null || txtHW2.getText().trim().equals(""))
		{
			exception.addError("HW2", "Field can't be empty");
		}
		obj.sethWord2(txtHW2.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtHW3.getText() == null || txtHW3.getText().trim().equals(""))
		{
			exception.addError("HW3", "Field can't be empty");
		}
		obj.sethWord3(txtHW3.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtHW4.getText() == null || txtHW4.getText().trim().equals(""))
		{
			exception.addError("HW4", "Field can't be empty");
		}
		obj.sethWord4(txtHW4.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtKWQ.getText() == null || txtKWQ.getText().trim().equals(""))
		{
			exception.addError("KWQ", "Field can't be empty");
		}
		obj.setkWordQuestion(txtKWQ.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtKW1.getText() == null || txtKW1.getText().trim().equals(""))
		{
			exception.addError("KW1", "Field can't be empty");
		}
		obj.setkWord1(txtKW1.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtKW2.getText() == null || txtKW2.getText().trim().equals(""))
		{
			exception.addError("KW2", "Field can't be empty");
		}
		obj.setkWord2(txtKW2.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtKW3.getText() == null || txtKW3.getText().trim().equals(""))
		{
			exception.addError("KW3", "Field can't be empty");
		}
		obj.setkWord3(txtKW3.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtKW4.getText() == null || txtKW4.getText().trim().equals(""))
		{
			exception.addError("KW4", "Field can't be empty");
		}
		obj.setkWord4(txtKW4.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtPt.getText() == null || txtPt.getText().trim().equals(""))
		{
			exception.addError("Pt", "Field can't be empty");
		}
		obj.setPortugueseTrad(txtPt.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		if(txtEng.getText() == null || txtEng.getText().trim().equals(""))
		{
			exception.addError("Eng", "Field can't be empty");
		}
		obj.setEnglishTrad(txtEng.getText());
		if(!exception.getErrors().isEmpty())
		{
			throw exception;
		}
		return obj;
	}

	@FXML
	public void onBtCancelAction(ActionEvent event)
	{
		Utils.currentStage(event).close();
	}
	
	public void setDepartment(Words entity) {
		this.entity = entity;
	}

	public void setDepartmentService(WordsService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener)
	{
		dataChangeListeners.add(listener);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes()
	{
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldInteger(txtLevel);
		Constraints.setTextFieldInteger(txtLesson);
		Constraints.setTextFieldInteger(txtHWA);
		Constraints.setTextFieldInteger(txtKWA);
		Constraints.setTextFieldMaxLength(txtHWQ, 30);
		Constraints.setTextFieldMaxLength(txtHW1, 30);
		Constraints.setTextFieldMaxLength(txtHW2, 30);
		Constraints.setTextFieldMaxLength(txtHW3, 30);
		Constraints.setTextFieldMaxLength(txtHW4, 30);
		Constraints.setTextFieldMaxLength(txtKWQ, 30);
		Constraints.setTextFieldMaxLength(txtKW1, 30);
		Constraints.setTextFieldMaxLength(txtKW2, 30);
		Constraints.setTextFieldMaxLength(txtKW3, 30);
		Constraints.setTextFieldMaxLength(txtKW4, 30);
		Constraints.setTextFieldMaxLength(txtEng, 30);
		Constraints.setTextFieldMaxLength(txtPt, 30);
	}
	
	public void updateFormData()
	{
		if(entity == null)
		{
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtLevel.setText(String.valueOf(entity.getLevel()));
		txtLesson.setText(String.valueOf(entity.getLesson()));
		txtHWA.setText(String.valueOf(entity.gethWordAnswer()));
		txtKWA.setText(String.valueOf(entity.getkWordAnswer()));
		txtHWQ.setText(entity.gethWordQuestion());
		txtHW1.setText(entity.gethWord1());
		txtHW2.setText(entity.gethWord2());
		txtHW3.setText(entity.gethWord3());
		txtHW4.setText(entity.gethWord4());
		txtKWQ.setText(entity.getkWordQuestion());
		txtKW1.setText(entity.getkWord1());
		txtKW2.setText(entity.getkWord2());
		txtKW3.setText(entity.getkWord3());
		txtKW4.setText(entity.getkWord4());
		txtEng.setText(entity.getEnglishTrad());
		txtPt.setText(entity.getPortugueseTrad());
	}

	private void setErrorMessages(Map<String, String> errors)
	{
		Set<String> fields = errors.keySet();
		if(fields.contains("HWQ"))
		{
			labelErrorHWQ.setText(errors.get("HWQ"));
		}
		if(fields.contains("HW1"))
		{
			labelErrorHW1.setText(errors.get("HW1"));
		}
		if(fields.contains("HW2"))
		{
			labelErrorHW2.setText(errors.get("HW2"));
		}
		if(fields.contains("HW3"))
		{
			labelErrorHW3.setText(errors.get("HW3"));
		}
		if(fields.contains("HW4"))
		{
			labelErrorHW4.setText(errors.get("HW4"));
		}
		if(fields.contains("KWQ"))
		{
			labelErrorKWQ.setText(errors.get("KWQ"));
		}
		if(fields.contains("KW1"))
		{
			labelErrorKW1.setText(errors.get("KW1"));
		}
		if(fields.contains("KW2"))
		{
			labelErrorKW2.setText(errors.get("KW2"));
		}
		if(fields.contains("KW3"))
		{
			labelErrorKW3.setText(errors.get("KW3"));
		}
		if(fields.contains("KW4"))
		{
			labelErrorKW4.setText(errors.get("KW4"));
		}
		if(fields.contains("Eng"))
		{
			labelErrorEng.setText(errors.get("Eng"));
		}
		if(fields.contains("Pt"))
		{
			labelErrorPt.setText(errors.get("Pt"));
		}
	}
}
