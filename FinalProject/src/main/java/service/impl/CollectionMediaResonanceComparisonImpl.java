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
public class CollectionMediaResonanceComparisonImpl implements CollectionChannelsDoubleInfo {
    public ObservableList<DoubleData> mediaResonanceList = FXCollections.observableArrayList();

    @Override
    public void add(Channel channel1, Channel channel2) {
        String date1 = channel1.getSnippet().getPublishedAt();
        String date2 = channel2.getSnippet().getPublishedAt();
        mediaResonanceList.add(new DoubleData("Channel title",
                channel1.getSnippet().getTitle(),
                channel2.getSnippet().getTitle()));
        mediaResonanceList.add(new DoubleData("Channel Id", channel1.getId(), channel2.getId()));
        mediaResonanceList.add(new DoubleData("Creation date",
                date1.substring(0, 4) + "." + date1.substring(5, 7) + "." + date1.substring(8, 10),
                date2.substring(0, 4) + "." + date2.substring(5, 7) + "." + date2.substring(8, 10)));
        mediaResonanceList.add(new DoubleData("Subscriber count",
                channel1.getStatistics().getSubscriberCount(),
                channel2.getStatistics().getSubscriberCount()));
        mediaResonanceList.add(new DoubleData("Video count",
                channel1.getStatistics().getVideoCount(),
                channel2.getStatistics().getVideoCount()));
        mediaResonanceList.add(new DoubleData("View count",
                channel1.getStatistics().getViewCount(),
                channel2.getStatistics().getViewCount()));
        mediaResonanceList.add(new DoubleData("Comment count",
                new ChannelServiceImpl().getCommentsCount(channel1.getId()) + "",
                new ChannelServiceImpl().getCommentsCount(channel2.getId()) + ""));
    }

    @Override
    public void removeAll() {
        mediaResonanceList.removeAll();
    }

}
