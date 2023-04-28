package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControlMain implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    public Button button_add;
    @FXML
    public Button button_edit;
    @FXML
    public Button button_paragraph;
    @FXML
    public Button button_translate;


    @FXML
    public void setStyleButton(Button button1,Button button2,Button button3 ,Button button4) {
        button1.setStyle("width: 200px;\n"
            + "  font-size: 16px;\n"
            + "  font-weight: 600;\n"
            + "  color: #fff;\n"
            + "  cursor: pointer;\n"
            + "  margin: 20px;\n"
            + "  height: 55px;\n"
            + "  text-align:center;\n"
            + "  border: #dde62a;\n"
            + "  background-size: 300% 100%;\n"
            + "\n"
            + "  border-radius: 50px;\n"
            + "  moz-transition: all .4s ease-in-out;\n"
            + "  -o-transition: all .4s ease-in-out;\n"
            + "  -webkit-transition: all .4s ease-in-out;\n"
            + "  transition: all .4s ease-in-out;"
            + "-fx-background-color: #A2C523");
        button2.setStyle("-fx-background-color: #86AC41");
        button3.setStyle("-fx-background-color: #86AC41");
        button4.setStyle("-fx-background-color: #86AC41");

    }

    @FXML
    private void buttonAddWord(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("controlAddWord.fxml"));
        anchorPane.getChildren().setAll(node);
        this.setStyleButton(button_add,button_edit,button_paragraph,button_translate);
    }

    @FXML
    private void buttonEditWord(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("editWord.fxml"));
        anchorPane.getChildren().setAll(node);
        this.setStyleButton(button_edit,button_add,button_translate,button_paragraph);
    }

    @FXML
    private void buttonSearchWord(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("sample.fxml"));
        anchorPane.getChildren().setAll(node);
        this.setStyleButton(button_translate,button_paragraph,button_edit,button_add);
    }

    @FXML
    private void buttonTranslateParagraph(ActionEvent event) throws IOException {
        Node node;
        node = FXMLLoader.load(getClass().getResource("ParagraphTranslate.fxml"));
        anchorPane.getChildren().setAll(node);
        this.setStyleButton(button_paragraph,button_translate,button_edit,button_add);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node node = null;
        try {
            node = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setStyleButton(button_translate,button_edit,button_add,button_paragraph);
        anchorPane.getChildren().setAll(node);

    }

}
