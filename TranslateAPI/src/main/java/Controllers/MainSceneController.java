package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import javafx.scene.control.TextArea;


public class MainSceneController implements Initializable {
    ObservableList<String> langs = FXCollections.observableArrayList(
            "af", "sq", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca", "ceb", "ny", "zh-TW", "hr",
            "cs", "da", "nl", "en", "eo", "et", "tl", "fi", "fr", "gl", "ka", "de", "el", "gu", "ht", "ha",
            "iw", "hi", "hmn", "hu", "is", "ig", "id", "ga", "it", "ja", "jw", "kn", "kk", "km", "ko", "lo",
            "la", "lv", "lt", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mn", "my", "ne", "no", "fa", "pl",
            "pt", "ro", "ru", "sr", "st", "si", "sk", "sl", "so", "es", "su", "sw", "sv", "tg", "ta", "te",
            "th", "tr", "uk", "ur", "uz", "vi", "cy", "yi", "yo", "zu");
    @FXML ChoiceBox langChoiceBox;
    @FXML
    TextArea initTextArea;
    @FXML TextArea procTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        langChoiceBox.setItems(langs);
    }



    public void process(ActionEvent actionEvent) throws GeneralSecurityException, IOException {
        Translate translate= new Translate.Builder(
                GoogleNetHttpTransport.newTrustedTransport()
                , GsonFactory.getDefaultInstance(), null)
                .setApplicationName("DemoTranslateAPI")
                .build();
        Translate.Translations.List list = translate.new Translations().list(
                Arrays.asList(initTextArea.getText().split("\n")), (String) langChoiceBox.getValue()
        );

        list.setKey("AIzaSyCnJ6oyAkMUZaPQYXwE6TfLi7QRHwKbjDE");
        TranslationsListResponse response = list.execute();
        for (TranslationsResource translationsResource : response.getTranslations())
        {
            procTextArea.setText(translationsResource.getTranslatedText());
        }
    }
}
