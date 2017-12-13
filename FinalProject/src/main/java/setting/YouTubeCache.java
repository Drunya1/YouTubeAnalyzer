package setting;

import entities.channel.ChannelResponse;
import lombok.ToString;
import java.util.ArrayList;

@ToString
public class YouTubeCache {
    private ArrayList<ChannelResponse> channels = new ArrayList<>();

    public ArrayList<ChannelResponse> getResponses() {
        return channels;
    }

    public void setResponses(ArrayList<ChannelResponse> responses) {
        this.channels = responses;
    }
}
