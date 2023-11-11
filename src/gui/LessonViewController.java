package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import model.entities.Option;
import model.entities.OptionRandom;
import model.entities.Question;
import model.entities.QuestionRandom;
import model.entities.Words;
import model.services.WordsService;

public class LessonViewController implements Initializable {

	private WordsService service;
	
	@FXML
	private ComboBox<String> comboBoxLessons;
	@FXML
	private ComboBox<String> comboBoxKanji;
	@FXML
	private ComboBox<String> difficulty;
	@FXML
	private Label userName;
	@FXML
	private Label questionType;
	@FXML
	private Label difficultyLevel;
	
	private ObservableList<String> obsList;
	
	private ObservableList<String> obsList2;
	
	private ObservableList<String> obsList3;
	
	@FXML
	public void onComboBoxLessonsAction()
	{
		String lesson = comboBoxLessons.getSelectionModel().getSelectedItem();
		Main.lesson = setLesson(lesson);
		comboBoxKanji.setVisible(true);
		questionType.setVisible(true);
	}
	
	@FXML
	public void onComboBoxKanjiAction()
	{
		String type = comboBoxKanji.getSelectionModel().getSelectedItem();
		Main.kanji = stringToBool(type);
		difficulty.setVisible(true);
		difficultyLevel.setVisible(true);
	}
	
	@FXML
	public void onComboBoxDifficultyAction()
	{
		String difficult = difficulty.getSelectionModel().getSelectedItem();
		Main.difLevel = setDifficulty(difficult);
		List<Words> words = service.findByLevelAndLesson(Main.level, Main.lesson);
		Option op1;
		Option op2;
		Option op3;
		Option op4;
		String quest;
		Question question;
		int answer;
		List<OptionRandom> opRand;
		List<QuestionRandom> qRand = new ArrayList<>();
		
		for(Words word : words)
		{
			if(Main.kanji)
			{
				quest = word.gethWordQuestion();
				answer = word.gethWordAnswer();
				op1 = new Option(word.gethWord1(), false);
				op2 = new Option(word.gethWord2(), false);
				op3 = new Option(word.gethWord3(), false);
				op4 = new Option(word.gethWord4(), false);
			}
			else
			{
				quest = word.getkWordQuestion();
				answer = word.getkWordAnswer();
				op1 = new Option(word.getkWord1(), false);
				op2 = new Option(word.getkWord2(), false);
				op3 = new Option(word.getkWord3(), false);
				op4 = new Option(word.getkWord4(), false);
			}
			switch(answer)
			{
			case 1:
				op1.setAnswer(true);
				break;
			case 2:
				op2.setAnswer(true);
				break;
			case 3:
				op3.setAnswer(true);
				break;
			default:
				op4.setAnswer(true);
			}
			opRand = new ArrayList<>();
			opRand.add(new OptionRandom(op1, random()));
			opRand.add(new OptionRandom(op2, random()));
			opRand.add(new OptionRandom(op3, random()));
			opRand.add(new OptionRandom(op4, random()));
			classifOpRandom(opRand);
			question = new Question(quest, opRand.get(0).getOption(), opRand.get(1).getOption(), opRand.get(2).getOption(), 
					opRand.get(3).getOption(), word.getEnglishTrad(), word.getPortugueseTrad());
			qRand.add(new QuestionRandom(question, random()));
		}
		classifQRandom(qRand);
		Main.questions = new ArrayList<>();
		for(QuestionRandom q : qRand)
		{
			Main.questions.add(q.getQuestion());
		}
		loadView("/gui/QuestionView.fxml");
	}
	
	public void setWordsService(WordsService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		userName.setText("Olá, " + Main.user.getName());
		List<String> list = new ArrayList<>();
		list.add("Semana 1 - Lição 1");
		list.add("Semana 1 - Lição 2");
		list.add("Semana 1 - Lição 3");
		list.add("Semana 1 - Lição 4");
		list.add("Semana 1 - Lição 5");
		list.add("Semana 1 - Lição 6");
		list.add("Semana 1 - Lição 7");
		list.add("Semana 2 - Lição 1");
		list.add("Semana 2 - Lição 2");
		list.add("Semana 2 - Lição 3");
		list.add("Semana 2 - Lição 4");
		list.add("Semana 2 - Lição 5");
		list.add("Semana 2 - Lição 6");
		list.add("Semana 2 - Lição 7");
		list.add("Semana 3 - Lição 1");
		list.add("Semana 3 - Lição 2");
		list.add("Semana 3 - Lição 3");
		list.add("Semana 3 - Lição 4");
		list.add("Semana 3 - Lição 5");
		list.add("Semana 3 - Lição 6");
		list.add("Semana 3 - Lição 7");
		list.add("Semana 4 - Lição 1");
		list.add("Semana 4 - Lição 2");
		list.add("Semana 4 - Lição 3");
		list.add("Semana 4 - Lição 4");
		list.add("Semana 4 - Lição 5");
		list.add("Semana 4 - Lição 6");
		list.add("Semana 4 - Lição 7");
		list.add("Semana 5 - Lição 1");
		list.add("Semana 5 - Lição 2");
		list.add("Semana 5 - Lição 3");
		list.add("Semana 5 - Lição 4");
		list.add("Semana 5 - Lição 5");
		list.add("Semana 5 - Lição 6");
		list.add("Semana 5 - Lição 7");
		list.add("Semana 6 - Lição 1");
		list.add("Semana 6 - Lição 2");
		list.add("Semana 6 - Lição 3");
		list.add("Semana 6 - Lição 4");
		list.add("Semana 6 - Lição 5");
		list.add("Semana 6 - Lição 6");
		list.add("Semana 6 - Lição 7");
		obsList = FXCollections.observableArrayList(list);
		comboBoxLessons.setItems(obsList);
		
		list = new ArrayList<>();
		list.add("Pergunta: Kanji");
		list.add("Pergunta: Hiragana");
		obsList2 = FXCollections.observableArrayList(list);
		comboBoxKanji.setItems(obsList2);
		
		list = new ArrayList<>();
		list.add("Easy");
		list.add("Normal");
		list.add("Hard");
		list.add("Very Hard");
		obsList3 = FXCollections.observableArrayList(list);
		difficulty.setItems(obsList3);

		Callback<ListView<String>, ListCell<String>> factory = lv -> new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty)
			{
				super.updateItem(item, empty);
				setText(empty ? "" : item);
			}
		};
		
		comboBoxLessons.setCellFactory(factory);
		comboBoxLessons.setButtonCell(factory.call(null));
		comboBoxKanji.setCellFactory(factory);
		comboBoxKanji.setButtonCell(factory.call(null));
		difficulty.setCellFactory(factory);
		difficulty.setButtonCell(factory.call(null));
		
		comboBoxKanji.setVisible(false);
		questionType.setVisible(false);
		difficulty.setVisible(false);
		difficultyLevel.setVisible(false);
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
	
	private Boolean stringToBool(String type)
	{
		if(type.equals("Pergunta: Kanji"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private Integer random()
	{
		return (int)(Math.random() * 10000);
	}
	
	private void classifOpRandom(List<OptionRandom> opRand)
	{
		OptionRandom rand;
		for(int i=0; i < opRand.size()-1; i++)
		{
			for(int j=i+1; j < opRand.size(); j++)
			{
				if(opRand.get(j).getRandom() > opRand.get(i).getRandom())
				{
					rand = opRand.get(i);
					opRand.set(i, opRand.get(j));
					opRand.set(j, rand);
				}
			}
		}
	}
	
	private void classifQRandom(List<QuestionRandom> opRand)
	{
		QuestionRandom rand;
		for(int i=0; i < opRand.size()-1; i++)
		{
			for(int j=i+1; j < opRand.size(); j++)
			{
				if(opRand.get(j).getRandom() > opRand.get(i).getRandom())
				{
					rand = opRand.get(i);
					opRand.set(i, opRand.get(j));
					opRand.set(j, rand);
				}
			}
		}
	}
	
	private int setDifficulty(String difficult)
	{
		switch(difficult)
		{
		case "Easy":
			return 1;
		case "Normal":
			return 2;
		case "Hard":
			return 3;
		default:
			return 4;
		}
	}
	
	private int setLesson(String choice)
	{
		String txt, join;
		int nmb, week, lesson;
		txt = "Semana";
		nmb = 1;
		join = txt + " " + nmb;
		if(choice.contains(join))
		{
			week = nmb;
		}
		else
		{
			nmb++;
			join = txt + " " + nmb;
			if(choice.contains(join))
			{
				week = nmb;
			}
			else
			{
				nmb++;
				join = txt + " " + nmb;
				if(choice.contains(join))
				{
					week = nmb;
				}
				else
				{
					nmb++;
					join = txt + " " + nmb;
					if(choice.contains(join))
					{
						week = nmb;
					}
					else
					{
						nmb++;
						join = txt + " " + nmb;
						if(choice.contains(join))
						{
							week = nmb;
						}
						else
						{
							nmb++;
							week = nmb;
						}
					}
				}
			}
		}
		txt = "Lição";
		nmb = 1;
		join = txt + " " + nmb;
		if(choice.contains(join))
		{
			lesson = nmb;
		}
		else
		{
			nmb++;
			join = txt + " " + nmb;
			if(choice.contains(join))
			{
				lesson = nmb;
			}
			else
			{
				nmb++;
				join = txt + " " + nmb;
				if(choice.contains(join))
				{
					lesson = nmb;
				}
				else
				{
					nmb++;
					join = txt + " " + nmb;
					if(choice.contains(join))
					{
						lesson = nmb;
					}
					else
					{
						nmb++;
						join = txt + " " + nmb;
						if(choice.contains(join))
						{
							lesson = nmb;
						}
						else
						{
							nmb++;
							lesson = nmb;
						}
					}
				}
			}
		}
		return ((7 * (week-1)) + lesson);
	}
}
