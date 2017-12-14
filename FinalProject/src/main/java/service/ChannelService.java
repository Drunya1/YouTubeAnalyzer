package service;

import entities.playlist.Playlist;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface ChannelService {

    ArrayList <Playlist> getChannelPlaylists(String channelId);

    ArrayList<String> getChannelVideosIds(String channelId) throws ExecutionException, InterruptedException;

    long getCommentsCount(String channelId);
}
