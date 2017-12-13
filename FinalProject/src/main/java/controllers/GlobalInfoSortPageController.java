package controllers;

import entities.channel.Channel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.entities.ChannelData;
import service.impl.CollectionChannelsInfoImpl;
import sorting.ChannelGenerator;
import sorting.SortType;
import sorting.SortingChannels;
import start.Main;
import util.Settings;

import java.io.IOException;
import java.util.ArrayList;

public class GlobalInfoSortPageController {
    @FXML
    public Button btnBack;
    public TextField textField;
    public Button btnSearch;
    public MenuItem miName;
    public MenuItem miDate;
    public MenuItem miSubscribers;
    public MenuItem miVideos;
    public MenuItem miViews;
    public AnchorPane content;
    public TableView table;
    public TableColumn<ChannelData,String> columnName;
    public TableColumn<ChannelData,String> columnId;
    public TableColumn<ChannelData,String> columnDate;
    public TableColumn<ChannelData,String> columnSubscribers;
    public TableColumn<ChannelData,String> columnVideos;
    public TableColumn<ChannelData,String> columnViews;
    public Label timeLabel;
    private ArrayList<Channel> channels;
    private CollectionChannelsInfoImpl channelGlobalInfo = new CollectionChannelsInfoImpl();
    private final static String DELIMITER = ",";

    public void moveToYTAnalytics(ActionEvent event) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxmls/analyticsMenu.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchChannels(ActionEvent event) {
        if (!textField.getText().equals("")) {
            if (Settings.checkTime) {
                Settings.startTime();
            }
            String channelIds = textField.getText().replaceAll(" ", "");
            String[] ids = channelIds.split(DELIMITER);
            channels = null;
            channels = ChannelGenerator.generateArrayChannels(ids);
            channelGlobalInfo.removeAll();
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }

            columnName.setCellValueFactory(new PropertyValueFactory<>("title"));
            columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            columnSubscribers.setCellValueFactory(new PropertyValueFactory<>("subscribers"));
            columnVideos.setCellValueFactory(new PropertyValueFactory<>("videos"));
            columnViews.setCellValueFactory(new PropertyValueFactory<>("views"));
            channelGlobalInfo.add(channels);
            table.setItems(channelGlobalInfo.getInfoList());
            if (Settings.checkTime) {
                timeLabel.setText(Settings.finishTime());
            }
        }
    }

    public void sortByName(ActionEvent event) {
        setTableItems(SortType.NAME);
    }

    public void sortByDate(ActionEvent event) {
        setTableItems(SortType.DATE);
    }

    public void sortBySubscribers(ActionEvent event) {
        setTableItems(SortType.SUBSCRIBERS);
    }

    public void sortByVideos(ActionEvent event) {
        setTableItems(SortType.VIDEOS);
    }

    public void sortByViews(ActionEvent event) {
        setTableItems(SortType.VIEWS);
    }

    public void setTableItems(SortType sortType){
        if (channels != null) {
            if (sortType == SortType.NAME) {
                channels = SortingChannels.sortByTitle(channels);
            } else if (sortType == SortType.DATE) {
                channels = SortingChannels.sortByDate(channels);
            } else if (sortType == SortType.SUBSCRIBERS){
                channels = SortingChannels.sortByCountSubscribers(channels);
            } else if (sortType == SortType.VIDEOS){
                channels = SortingChannels.sortByCountVideos(channels);
            } else if (sortType == SortType.VIEWS){
                channels = SortingChannels.sortByViewCount(channels);
            }
            for (int i = 0; i < table.getItems().size(); i++) {
                table.getItems().clear();
            }
            channelGlobalInfo.add(channels);
            table.setItems(channelGlobalInfo.getInfoList());
        }
    }
}
