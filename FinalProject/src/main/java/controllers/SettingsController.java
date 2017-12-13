package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import start.Main;
import util.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    public Button btnBack;
    public CheckBox checkBoxSaveCache;
    public CheckBox checkBoxTimeRequest;
    public TextField txtCachePath;
    public Button btnApply;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkBoxTimeRequest.setOnAction(s -> Settings.checkTime = true);
        checkBoxSaveCache.setOnAction(s -> Settings.checkCache = true);
    }

    public void moveToMainMenu(ActionEvent event) { // todo add btn save, and save configuration to setting.txt
        Settings.cachePath = txtCachePath.getText();
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/mainMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void applySettings(ActionEvent event) {
    }
}