package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import start.Main;
import util.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChannelController implements Initializable {

    public Button btnYTAnalytics;
    public Button btnSettings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Settings.checkCurrentConfiguration();
    }

    public void moveToAnalytics(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analyticsMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToSettings(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/settings.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}