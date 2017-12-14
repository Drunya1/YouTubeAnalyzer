package service.impl;

import entities.channel.Channel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import service.CollectionChannelsDoubleInfo;
import service.entities.DoubleData;

@Getter
@Setter
public class CollectionGlobalInfoComparisonImpl implements CollectionChannelsDoubleInfo {
    public ObservableList<DoubleData> globalInfoList = FXCollections.observableArrayList();

    @Override
    public void add(Channel channel1, Channel channel2) {
        String date1 = channel1.getSnippet().getPublishedAt();
        String date2 = channel2.getSnippet().getPublishedAt();
        globalInfoList.add(new DoubleData("Channel title",
                channel1.getSnippet().getTitle(),
                channel2.getSnippet().getTitle()));
        globalInfoList.add(new DoubleData("Channel Id", channel1.getId(), channel2.getId()));
        globalInfoList.add(new DoubleData("Creation date",
                date1.substring(0, 4) + "." + date1.substring(5, 7) + "." + date1.substring(8, 10),
                date2.substring(0, 4) + "." + date2.substring(5, 7) + "." + date2.substring(8, 10)));
        globalInfoList.add(new DoubleData("Subscriber count",
                channel1.getStatistics().getSubscriberCount(),
                channel2.getStatistics().getSubscriberCount()));
        globalInfoList.add(new DoubleData("Video count",
                channel1.getStatistics().getVideoCount(),
                channel2.getStatistics().getVideoCount()));
        globalInfoList.add(new DoubleData("View count",
                channel1.getStatistics().getViewCount(),
                channel2.getStatistics().getViewCount()));
    }

    @Override
    public void removeAll() {
        globalInfoList.removeAll();
    }
}
