package main;
/**
 * controller dung de dieu khien file sample.fxml sample.fxml co the sua bang open screenbuider
 */

import java.beans.PropertyVetoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.speech.AudioException;
import javax.speech.EngineException;
import main.java.backend.DictionaryManagement;
import main.java.backend.IOMysql;

public class ControllerSearch {

    private DictionaryManagement dic = new DictionaryManagement();
    @FXML
    protected TextField Height;
    @FXML
    protected TextArea textArea;
    @FXML
    protected ListView<String> listView;

    private boolean checkInterNet (){
        try {
            URL url = new URL("https://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
           return true;
        } catch (MalformedURLException e) {
           return false;
        } catch (IOException e) {
           e.printStackTrace();
        }
        return  false;
    }
    /**
     * initialize function, which is call every time you switch to 'search' tab.
     *
     * this block is design to init the dictionary of 'dic' only one -> reduce the time complexity
     * if you want to switch of to online mode you need to restart whole application
     *
     * attribute 'dic' in class 'DictionaryManagement' is static so when you modify the dictionary (which
     * is story in a Trie data structure you also change the 'dic' attribute in all other Class
     */
    {
        //TODO thêm tính năng tùy chỉnh kết nối mạng, cải tiến đồ họa
        if(dic.getDictionary() == null) {
            try {
                this.dic.InsertOfflineData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateCSDL(ActionEvent event) {
        try {
            IOMysql mysql = new IOMysql();
            mysql.importOnline(dic);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ERROR!, Không thể kết nối đến cơ sở dữ liệu từ điển!");
            alert.show();
        }
    }

    /**
     * cai nay la nhap tu vao textField
     */
    public void inputText(KeyEvent event) {
        String word_Input = Height.getText().trim();
        if (!word_Input.equals("")) {
            listView.getItems().setAll(dic.dictionarySearcher(word_Input));
            if(listView.getItems().size() == 0 && word_Input.charAt(0) != ' ')
                listView.getItems().add("Word " + word_Input + " is currently not in our dictionary!");
            if (dic.searchWord(word_Input) != null) {
                textArea.setText(dic.searchWord(word_Input));
            }
        }
        else {
            textArea.clear();
        }
    }

    /**
     * cai nay dung chuot chon tu nhung tu goi y
     */
    @FXML
    public void HandleMouseListWord(MouseEvent event) {
        String word = listView.getSelectionModel().getSelectedItem();
        if (word == null || word.equals("")) {
            return;
        } else {
            Height.setText(word);
            textArea.setText(dic.searchWord(word));
        }
    }

    public void HandleButtonSpeech(ActionEvent event)
            throws PropertyVetoException, AudioException, EngineException, InterruptedException {
        String word = Height.getText();
        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.init("kevin16");
        textToSpeech.doSpeak(word);
    }
}
