package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import start.Main;

import java.io.IOException;

public class YouTubeAnalyticsController {
    @FXML
    public Button btnBack;
    public Button btnGlobalInfo;
    public Button btnCompareGlobalInfo;
    public Button btnSortChannels;
    public Button btnMediaResonance;
    public Button btnMediaResonanceCompare;
    public Button btnMediaResonanceSort;
    public Label timeLabel;

    public void moveToGlobalInfoComparePage(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/globalInfoComparePage.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToGlobalInfoSort(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/globalInfoSort.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToMediaResonancePage(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/mediaResonancePage.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToMediaResonanceCompare(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/mediaResonanceComparePage.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void moveToMediaResonanceSort(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/mediaResonanceSort.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToGlobalInfoPage(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/globalInfoPage.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToMainMenu(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/mainMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
