package controllers;

import entities.channel.Channel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.entities.ChannelData;
import service.impl.CollectionChannelsInfoImpl;
import sorting.ChannelGenerator;
import sorting.SortingChannels;
import start.Main;
import util.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MediaResonanceSortPageController implements Initializable {
    @FXML
    public Button btnBack;
    public TextField textField;
    public Button btnSearch;
    public TableView table;
    public TableColumn<ChannelData,String> columnName;
    public TableColumn<ChannelData,String> columnId;
    public TableColumn<ChannelData,String> columnDate;
    public TableColumn<ChannelData,String> columnSubscribers;
    public TableColumn<ChannelData,String> columnVideos;
    public TableColumn<ChannelData,String> columnViews;
    public TableColumn<ChannelData,String> columnComments;
    public Label timeLabel;
    private ArrayList<Channel> channels;
    private CollectionChannelsInfoImpl mediaResonanceInfo = new CollectionChannelsInfoImpl();
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
        btnSearch.setOnAction(e -> new Thread(this::searchChannels).start());
    }

    public void searchChannels() {
//        UC_x5XG1OV2P6uZZ5FSM9Ttw, UC6zhI71atP7YLoZyIyCIGNw,  UCORRUYUmW1pffMgLPzU0XCA, UC-lHJZR3Gqxm24_Vd_AJ5Yw
        if (!textField.getText().equals("")) {
            if (Settings.checkTime) {
                Settings.startTime();
            }
            String channelIds = textField.getText().replaceAll(" ", "");
            String[] ids = channelIds.split(DELIMITER);
            channels = null;
            channels = ChannelGenerator.generateArrayChannels(ids);
            SortingChannels.sortByCommentCount(channels);
            mediaResonanceInfo.removeAll();
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }

            columnName.setCellValueFactory(new PropertyValueFactory<>("title"));
            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            columnSubscribers.setCellValueFactory(new PropertyValueFactory<>("subscribers"));
            columnVideos.setCellValueFactory(new PropertyValueFactory<>("videos"));
            columnViews.setCellValueFactory(new PropertyValueFactory<>("views"));
            columnComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
            mediaResonanceInfo.addMediaResonance(channels);
            table.setItems(mediaResonanceInfo.getInfoList());
            Platform.runLater(() -> {
                if (Settings.checkTime) {
                    timeLabel.setText(Settings.finishTime());
                }
            });
        }
    }
}
