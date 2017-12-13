package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import start.Main;

import java.io.IOException;

public class ChannelController {

    public Button btnYTAnalytics;
    public Button btnSettings;

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
