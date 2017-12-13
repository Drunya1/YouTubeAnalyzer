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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    public Button btnBack;
    public CheckBox checkBoxSaveCache;
    public CheckBox checkBoxTimeRequest;
    public TextField txtCachePath;
    @FXML
    public Button btnSave;

    private static final String SETTING_PATH = "src/main/resources/setting/setting.txt"; // replace for windows

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCurrentConfiguration();
        Settings.checkCurrentConfiguration();
        checkBoxTimeRequest.setOnAction(s -> Settings.checkTime = !Settings.checkTime);
        checkBoxSaveCache.setOnAction(s -> Settings.checkCache = !Settings.checkCache);
        btnSave.setOnAction(s -> saveSetting());
    }

    private void getCurrentConfiguration() {
        try (final BufferedReader reader = new BufferedReader(new FileReader(SETTING_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(":");
                switch (split[0]) {
                    case "checkTime":
                        if (split[1].equals("1")) {
                            checkBoxTimeRequest.setSelected(true);
                        } else {
                            checkBoxTimeRequest.setSelected(false);
                        }
                        break;
                    case "checkCache":
                        if (split[1].equals("1")) {
                            checkBoxSaveCache.setSelected(true);
                        } else {
                            checkBoxSaveCache.setSelected(false);
                        }
                        break;
                    case "cachePath":
                        txtCachePath.setText(split[1]);
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveSetting() {
        Settings.cachePath = txtCachePath.getText();
        int checkTimeValue = 0;
        int checkCacheValue = 0;
        if (Settings.checkTime) {
            checkTimeValue = 1;
        }
        if (Settings.checkCache) {
            checkCacheValue = 1;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("checkTime:")
                .append(checkTimeValue)
                .append("\n")
                .append("checkCache:")
                .append(checkCacheValue)
                .append("\n")
                .append("cachePath:")
                .append(txtCachePath.getText());
        try {
            Files.write(Paths.get(SETTING_PATH), builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToMainMenu(ActionEvent event) { // todo add btn save, and save configuration to setting.txt
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/mainMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}