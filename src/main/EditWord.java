package main;

import main.java.backend.DictionaryManagement;
import main.java.backend.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditWord {

    private DictionaryManagement dictionaryManagement = new DictionaryManagement();
    @FXML
    TextField OldWord;
    @FXML
    TextField NewWord;
    @FXML
    TextArea textArea;

    public EditWord() {

    }

    private boolean check(String wordTarget) {
        return dictionaryManagement.checkWord(wordTarget);
    }

    public void edit(ActionEvent event) throws IOException {
        String oldWord = OldWord.getText().trim();
        String newWordTarget = NewWord.getText().trim();
        String newWordExplain = textArea.getText().trim();
        if (!check(oldWord)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Error ,word " + oldWord + " currently is not in our dictionary");
            alert.show();
        } else {
            try {
                Word newWord = new Word(newWordTarget, newWordExplain);
                dictionaryManagement.getDictionary().erase(oldWord);
                if(!newWordTarget.equals("")) dictionaryManagement.getDictionary().add(newWord);
                //more code
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("Success!");
                alert.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
