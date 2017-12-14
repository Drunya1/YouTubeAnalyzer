package sorting;

import client.YouTubeClient;
import entities.channel.Channel;
import entities.channel.ChannelResponse;
import java.util.ArrayList;

public class ChannelGenerator {
    private ChannelGenerator(){}

    public static ArrayList<Channel> generateArrayChannels(String[] channelIdentifiers){
        ArrayList<Channel> channels = new ArrayList<>();
        for (String channelIdentifier : channelIdentifiers) {
            ChannelResponse response = YouTubeClient.getChannelInfo(channelIdentifier);
            assert response != null;
            channels.add(response.getItems().get(0));
        }
        return channels;
    }
}
