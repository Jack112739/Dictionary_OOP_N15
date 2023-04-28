package main;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ParagraphTranslateController {
    @FXML
    protected TextArea English;
    @FXML
    protected TextArea Vietnamese;
    @FXML
    protected TextField langFrom;
    @FXML
    protected TextField langTo;
    protected googleTranslateSt google = new googleTranslateSt();
    public void inputText(ActionEvent event) throws IOException {
        String langto = langTo.getText();
        String langfrom = langFrom.getText();
        if (langfrom.equals("")||langto.equals(""))  {
            langfrom = "en";
            langto = "vi";
        }
        String Eng = English.getText();
        String Vie = google.tranWithGoogle(Eng,langfrom,langto);
        Vietnamese.setText(Vie);
        if (Eng == null || Eng.equals("")) {
            Vietnamese.clear();
        }
    }

}
