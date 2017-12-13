package controllers;

import client.YouTubeClient;
import entities.channel.Channel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.entities.Data;
import service.impl.CollectionChannelGlobalInfoImpl;
import start.Main;
import util.Settings;

import java.io.IOException;

public class GlobalInfoPageController {
    @FXML
    public Button btnBack;
    public TextField textField;
    public Button btnSearch;
    public TableView table;
    public TableColumn<Data,String> columnTypeOfInfo;
    public TableColumn<Data,String> columnValue;
    public Label timeLabel;
    private CollectionChannelGlobalInfoImpl channelGlobalInfo = new CollectionChannelGlobalInfoImpl();

    public void moveToYTAnalytics(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analyticsMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchChannel(ActionEvent event) {
        if (!textField.getText().equals("")) {
            if (Settings.checkTime) {
                Settings.startTime();
            }
            Channel channel = YouTubeClient.getChannelInfo(textField.getText()).getItems().get(0);
            if (channel != null) {
                channelGlobalInfo.removeAll();
                for (int i = 0; i < table.getItems().size(); i++) {
                    table.getItems().clear();
                }
                
                columnTypeOfInfo.setCellValueFactory(new PropertyValueFactory<>("typeOfInfo"));
                columnValue.setCellValueFactory(new PropertyValueFactory<>("value"));
                channelGlobalInfo.add(channel);
                table.setItems(channelGlobalInfo.getGlobalInfoList());
            }
            if (Settings.checkTime) {
                timeLabel.setText(Settings.finishTime());
            }
        }
    }
}
