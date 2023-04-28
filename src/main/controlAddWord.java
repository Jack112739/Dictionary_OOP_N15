package main;

import main.java.backend.DictionaryManagement;
import main.java.backend.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class controlAddWord {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    @FXML
    private TextField InputAddWordTarget = new TextField();
    @FXML
    private TextArea InputAddWordExplain = new TextArea();
    @FXML
    public Button button_add;

    public controlAddWord() {
    }

    private boolean check(String wordTarget) {
        return !dictionaryManagement.checkWord(wordTarget);
    }

    @FXML
    public void saveWord(ActionEvent event) {
        String WordTarget = InputAddWordTarget.getText().trim();
        String WordExplain = InputAddWordExplain.getText().trim();
        Word wordNew = new Word(WordTarget, WordExplain);
        if(WordTarget.equals("")) return;
        if (check(WordTarget)) {
            dictionaryManagement.getDictionary().add(wordNew);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("word " + WordTarget + " has been successfully added");
            alert.show();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("ERROR!, word " + WordTarget + " is already in our dictionary");
            alert.show();
        }
    }
}


