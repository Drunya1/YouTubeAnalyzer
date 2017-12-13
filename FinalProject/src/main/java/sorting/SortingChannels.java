package sorting;

import entities.channel.Channel;
import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.Comparator;

public class SortingChannels {
    private SortingChannels(){}

    public static ArrayList<Channel> sortByTitle(ArrayList<Channel> channels){
        channels.sort(Comparator.comparing(channel -> channel.getSnippet().getTitle()));
        return channels;
    }

    public static ArrayList<Channel> sortByDate(ArrayList<Channel> channels){
        channels.sort(Comparator.comparing(channel ->
                DatatypeConverter.parseDateTime(channel.getSnippet().getPublishedAt()).getTime()));
        return channels;
    }

    public static ArrayList<Channel> sortByCountSubscribers(ArrayList<Channel> channels){
        channels.sort((channel1, channel2) ->
                (int) (Double.parseDouble(channel1.getStatistics().getSubscriberCount())
                 - Double.parseDouble(channel2.getStatistics().getSubscriberCount())));
        return channels;
    }

    public static ArrayList<Channel> sortByCountVideos(ArrayList<Channel> channels){
        channels.sort(Comparator.comparingInt(channel ->
                Integer.parseInt(channel.getStatistics().getVideoCount())));
        return channels;
    }

    public static ArrayList<Channel> sortByViewCount(ArrayList<Channel> channels){
        channels.sort((channel1, channel2) ->
                (int) (Double.parseDouble(channel1.getStatistics().getViewCount())
                        - Double.parseDouble(channel2.getStatistics().getViewCount())));
        return channels;
    }

    public static ArrayList<Channel> sortByCommentCount(ArrayList<Channel> channels){
        channels.sort(Comparator.comparingLong(Channel::getCommentCount));
        return channels;
    }
}
