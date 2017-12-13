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
import service.entities.DoubleData;
import service.impl.CollectionMediaResonanceComparisonImpl;
import start.Main;
import util.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaResonanceComparePageController implements Initializable {
    @FXML
    public Button btnBack;
    public TextField textField;
    public Button btnSearch;
    public TableView table;
    public TableColumn<DoubleData,String> columnTypeOfInfo;
    public TableColumn<DoubleData,String> columnValue1;
    public TableColumn<DoubleData,String> columnValue2;
    public Label timeLabel;
    private CollectionMediaResonanceComparisonImpl mediaResonanceInfo = new CollectionMediaResonanceComparisonImpl();
    private final static String DELIMITER = ",";

    public void moveToYTAnalytics(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analyticsMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSearch.setOnAction(s -> new Thread(this::searchChannels).start());
    }

    public void searchChannels() {
        if (!textField.getText().equals("")) {
            if (Settings.checkTime) {
                Settings.startTime();
            }
            String channels = textField.getText();
            channels = channels.replaceAll(" ", "");
            Channel channel1 = YouTubeClient.getChannelInfo(channels.substring(0, channels.indexOf(DELIMITER)))
                    .getItems().get(0);
            Channel channel2 = YouTubeClient.getChannelInfo(channels.substring(channels.indexOf(DELIMITER) + 1,
                    channels.length())).getItems().get(0);
            mediaResonanceInfo.removeAll();
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }

            columnTypeOfInfo.setCellValueFactory(new PropertyValueFactory<>("typeOfInfo"));
            columnValue1.setCellValueFactory(new PropertyValueFactory<>("value1"));
            columnValue2.setCellValueFactory(new PropertyValueFactory<>("value2"));
            mediaResonanceInfo.add(channel1, channel2);
            table.setItems(mediaResonanceInfo.getMediaResonanceList());
            Platform.runLater(() -> {
                if (Settings.checkTime) {
                    timeLabel.setText(Settings.finishTime());
                }
            });
        }
    }
}