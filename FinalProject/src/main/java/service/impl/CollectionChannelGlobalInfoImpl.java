package service.impl;

import entities.channel.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import service.CollectionChannelInfo;
import service.entities.Data;

@Getter
@Setter
public class CollectionChannelGlobalInfoImpl implements CollectionChannelInfo {
    public ObservableList<Data> globalInfoList = FXCollections.observableArrayList();

    @Override
    public void add(Channel channel) {
        String date = channel.getSnippet().getPublishedAt();
        globalInfoList.add(new Data("Channel title", channel.getSnippet().getTitle()));
        globalInfoList.add(new Data("Channel Id", channel.getId()));
        globalInfoList.add(new Data("Creation date", date.substring(0, 4) + "." + date.substring(5, 7) + "."
                + date.substring(8, 10)));
        globalInfoList.add(new Data("Subscriber count", channel.getStatistics().getSubscriberCount()));
        globalInfoList.add(new Data("Video count", channel.getStatistics().getVideoCount()));
        globalInfoList.add(new Data("View count", channel.getStatistics().getViewCount()));
    }

    @Override
    public void removeAll() {
        globalInfoList.removeAll();
    }
}
