package service.impl;

import entities.channel.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import service.CollectionChannelsInfo;
import service.entities.ChannelData;
import java.util.ArrayList;

@Getter
@Setter
public class CollectionChannelsInfoImpl implements CollectionChannelsInfo {
    public ObservableList<ChannelData> infoList = FXCollections.observableArrayList();

    @Override
    public void add(ArrayList<Channel> channels) {
        for (Channel channel : channels) {
            String date = channel.getSnippet().getPublishedAt();
            infoList.add(new ChannelData(channel.getSnippet().getTitle(),
                    channel.getId(),
                    date.substring(0, 4) + "." + date.substring(5, 7) + "." + date.substring(8, 10),
                    channel.getStatistics().getSubscriberCount(),
                    channel.getStatistics().getVideoCount(),
                    channel.getStatistics().getViewCount(),
                    null));
        }
    }

    @Override
    public void addMediaResonance(ArrayList<Channel> channels) {
        add(channels);
        for (int i = 0; i < channels.size(); i++) {
            long commentCount = new ChannelServiceImpl().getCommentsCount(channels.get(i).getId());
            infoList.get(i).setComments(commentCount + "");
        }
    }

    @Override
    public void removeAll() {
        infoList.removeAll();
    }
}
