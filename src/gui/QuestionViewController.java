package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.entities.Question;

public class QuestionViewController implements Initializable {
	
	@FXML
	private Label userName;
	@FXML
	private Label points;
	@FXML
	private Label labelQuestion;
	@FXML
	private Label pergunta;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label labelAnswer;
	@FXML
	private Label labelEng;
	@FXML
	private Label labelPt;
	@FXML
	private RadioButton bOp1;
	@FXML
	private RadioButton bOp2;
	@FXML
	private RadioButton bOp3;
	@FXML
	private RadioButton bOp4;
	@FXML
	private Button nextQ;
	
	@FXML
	public void onBOp1Action()
	{
		if(Main.questions.get(Main.sequence).getOption1().getAnswer())
		{
			bOp1.setTextFill(Color.GREEN);
			int pts = Main.user.getPoints() + 1;
			Main.user.setPoints(pts);
		}
		else
		{
			bOp1.setTextFill(Color.RED);
		}
		bOp1.setDisable(true);
		bOp2.setDisable(true);
		bOp3.setDisable(true);
		bOp4.setDisable(true);
		label1.setVisible(true);
		label2.setVisible(true);
		label3.setVisible(true);
		labelAnswer.setVisible(true);
		labelEng.setVisible(true);
		labelPt.setVisible(true);
		nextQ.setVisible(true);
	}
	
	@FXML
	public void onBOp2Action()
	{
		if(Main.questions.get(Main.sequence).getOption2().getAnswer())
		{
			bOp2.setTextFill(Color.GREEN);
			int pts = Main.user.getPoints() + 1;
			Main.user.setPoints(pts);
		}
		else
		{
			bOp2.setTextFill(Color.RED);
		}
		bOp1.setDisable(true);
		bOp2.setDisable(true);
		bOp3.setDisable(true);
		bOp4.setDisable(true);
		label1.setVisible(true);
		label2.setVisible(true);
		label3.setVisible(true);
		labelAnswer.setVisible(true);
		labelEng.setVisible(true);
		labelPt.setVisible(true);
		nextQ.setVisible(true);
	}
	
	@FXML
	public void onBOp3Action()
	{
		if(Main.questions.get(Main.sequence).getOption3().getAnswer())
		{
			bOp3.setTextFill(Color.GREEN);
			int pts = Main.user.getPoints() + 1;
			Main.user.setPoints(pts);
		}
		else
		{
			bOp3.setTextFill(Color.RED);
		}
		bOp1.setDisable(true);
		bOp2.setDisable(true);
		bOp3.setDisable(true);
		bOp4.setDisable(true);
		label1.setVisible(true);
		label2.setVisible(true);
		label3.setVisible(true);
		labelAnswer.setVisible(true);
		labelEng.setVisible(true);
		labelPt.setVisible(true);
		nextQ.setVisible(true);
	}
	
	@FXML
	public void onBOp4Action()
	{
		if(Main.questions.get(Main.sequence).getOption4().getAnswer())
		{
			bOp4.setTextFill(Color.GREEN);
			int pts = Main.user.getPoints() + 1;
			Main.user.setPoints(pts);
		}
		else
		{
			bOp4.setTextFill(Color.RED);
		}
		bOp1.setDisable(true);
		bOp2.setDisable(true);
		bOp3.setDisable(true);
		bOp4.setDisable(true);
		label1.setVisible(true);
		label2.setVisible(true);
		label3.setVisible(true);
		labelAnswer.setVisible(true);
		labelEng.setVisible(true);
		labelPt.setVisible(true);
		nextQ.setVisible(true);
	}
	
	@FXML
	public void onNextQAction()
	{
		Main.sequence++;
		if(Main.sequence < Main.questions.size())
		{
			loadView("/gui/QuestionView.fxml");
		}
		else
		{
			loadView("/gui/ResultView.fxml");
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		userName.setText(Main.user.getName());
		points.setText(String.valueOf(Main.user.getPoints()));
		labelQuestion.setText((Main.sequence+1) + " de " + Main.questions.size());
		pergunta.setText(Main.questions.get(Main.sequence).getQuestion());
		labelAnswer.setText(getAnswer(Main.questions.get(Main.sequence)));
		labelEng.setText(Main.questions.get(Main.sequence).getEngTrad());
		labelPt.setText(Main.questions.get(Main.sequence).getPtTrad());
		bOp1.setText(Main.questions.get(Main.sequence).getOption1().getWord());
		bOp2.setText(Main.questions.get(Main.sequence).getOption2().getWord());
		bOp3.setText(Main.questions.get(Main.sequence).getOption3().getWord());
		bOp4.setText(Main.questions.get(Main.sequence).getOption4().getWord());
		if(Main.sequence < Main.questions.size() - 1)
		{
			nextQ.setText("Próxima Questão");
		}
		else
		{
			nextQ.setText("Resultado");
		}
		label1.setVisible(false);
		labelAnswer.setVisible(false);
		nextQ.setVisible(false);
		getDifficult();
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
	
	private void getDifficult()
	{
		switch(Main.difLevel)
		{
		case 1:
			label2.setVisible(true);
			label3.setVisible(true);
			labelEng.setVisible(true);
			labelPt.setVisible(true);
			break;
		case 2:
			label2.setVisible(false);
			label3.setVisible(true);
			labelEng.setVisible(false);
			labelPt.setVisible(true);
			break;
		case 3:
			label2.setVisible(true);
			label3.setVisible(false);
			labelEng.setVisible(true);
			labelPt.setVisible(false);
			break;
		default:
			label2.setVisible(false);
			label3.setVisible(false);
			labelEng.setVisible(false);
			labelPt.setVisible(false);
		}
	}
	
	private String getAnswer(Question question)
	{
		if(question.getOption1().getAnswer())
		{
			return question.getOption1().getWord();
		}
		else
		{
			if(question.getOption2().getAnswer())
			{
				return question.getOption2().getWord();
			}
			else
			{
				if(question.getOption3().getAnswer())
				{
					return question.getOption3().getWord();
				}
				else
				{
					return question.getOption4().getWord();
				}
			}
		}
	}
}
