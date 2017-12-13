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
public class CollectionChannelMediaResonanceImpl implements CollectionChannelInfo {
    public ObservableList<Data> mediaResonanceInfo = FXCollections.observableArrayList();

    @Override
    public void add(Channel channel) {
        String date = channel.getSnippet().getPublishedAt();
        mediaResonanceInfo.add(new Data("Channel title", channel.getSnippet().getTitle()));
        mediaResonanceInfo.add(new Data("Channel Id", channel.getId()));
        mediaResonanceInfo.add(new Data("Creation date", date.substring(0, 4) + "." + date.substring(5, 7) + "."
                + date.substring(8, 10)));
        mediaResonanceInfo.add(new Data("Subscriber count", channel.getStatistics().getSubscriberCount()));
        mediaResonanceInfo.add(new Data("Video count", channel.getStatistics().getVideoCount()));
        mediaResonanceInfo.add(new Data("View count", channel.getStatistics().getViewCount()));
        long commentCount = new ChannelServiceImpl().getCommentsCount(channel.getId());
        mediaResonanceInfo.add(new Data("Comment count",  commentCount + ""));
    }

    @Override
    public void removeAll() {
        mediaResonanceInfo.removeAll();
    }
}
