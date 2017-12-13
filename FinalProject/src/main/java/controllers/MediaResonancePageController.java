package controllers;

import client.YouTubeClient;
import entities.channel.Channel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.entities.Data;
import service.impl.CollectionChannelMediaResonanceImpl;
import start.Main;
import util.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaResonancePageController implements Initializable {
    @FXML
    public Button btnBack;
    public TextField textField;
    public Button btnSearch;
    public TableView table;
    public TableColumn<Data,String> columnTypeOfInfo;
    public TableColumn<Data,String> columnValue;
    public Label timeLabel;
    public CollectionChannelMediaResonanceImpl mediaResonanceInfo = new CollectionChannelMediaResonanceImpl();

    public void moveToYTAnalytics(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analyticsMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSearch.setOnAction(e -> {
//            if (Settings.checkTime) {
//                Settings.startTime();
//            }
            Thread thread = new Thread(this::searchChannel);
            thread.start();
//            try {
//                thread.join();
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//            if (Settings.checkTime) {
//                timeLabel.setText("Spent time " + Settings.finishTime());
//            }
        });
    }

    public void searchChannel() {
        if (!textField.getText().equals("")) {
            timeLabel.setText("");
            if (Settings.checkTime) {
                Settings.startTime();
            }
            Channel channel = YouTubeClient.getChannelInfo(textField.getText()).getItems().get(0);
            if (channel != null) {
                mediaResonanceInfo.removeAll();
                for (int i = 0; i < table.getItems().size(); i++) {
                    table.getItems().clear();
                }

                columnTypeOfInfo.setCellValueFactory(new PropertyValueFactory<>("typeOfInfo"));
                columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
                mediaResonanceInfo.add(channel);
                table.setItems(mediaResonanceInfo.getMediaResonanceInfo());
            }
            Platform.runLater(() -> {
                if (Settings.checkTime) {
                    timeLabel.setText(Settings.finishTime());
                }
            });
        }
    }
}
