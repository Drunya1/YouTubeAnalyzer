package service.impl;

import entities.channel.Channel;
import entities.playlist.Playlist;
import entities.video.Video;
import service.ChannelService;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ChannelServiceImpl implements ChannelService {

    @Override
    public ArrayList<Playlist> getChannelPlaylists(String channelId) {
        return null;
    }

    @Override
    public ArrayList<String> getChannelVideosIds(String channelId) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public long getCommentsCount(String channelId) {
        ArrayList<Video> videos = null;
        try {
            videos = Channel.getChannelVideos(Channel.getChannelVideosIds(channelId));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        int commentCount = 0;

        assert videos != null;
        for (Video video : videos) {
            if (video.getStatistics().getCommentCount() != null) {
                commentCount += Integer.parseInt(video.getStatistics().getCommentCount());
            }
        }
        return commentCount;
    }
}
