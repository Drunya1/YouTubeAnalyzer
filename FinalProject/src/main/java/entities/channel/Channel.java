package entities.channel;

import client.YouTubeClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entities.Snippet;
import entities.playlist.Playlist;
import entities.playlist.PlaylistResponse;
import entities.video.Video;
import entities.video.VideoResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {
    private String id;
    private Snippet snippet;
    private ChannelStatistics statistics;
    private long commentCount;

    public static ArrayList<Playlist> getChannelPlaylists(String channelId){
        ArrayList<Playlist> playlists = new ArrayList<>();
        PlaylistResponse response = YouTubeClient.getChannelPlaylist(channelId, null);
        assert response != null;
        playlists.addAll(response.getItems());
        String nextPageToken = response.getNextPageToken();

        while (nextPageToken != null){
            response = YouTubeClient.getChannelPlaylist(channelId, nextPageToken);
            assert response != null;
            playlists.addAll(response.getItems());
            nextPageToken = response.getNextPageToken();
        }
        return playlists;
    }

    public static ArrayList <String> getChannelVideosIds(String channelId) throws ExecutionException, InterruptedException {
        ArrayList<Playlist> response = Channel.getChannelPlaylists(channelId);
        ArrayList<String> videosIds = new ArrayList<>();
        System.out.println(response.size());
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<ArrayList<String>> callable = () -> {
            for (int i = 0; i < response.size() / 2; i++) {
            videosIds.addAll(Playlist.getPlaylistItemsIds(response.get(i).getId()));
            }
            return videosIds;
        };

        Callable<ArrayList<String>> callable2 = () -> {
            for (int i = response.size() / 2; i < response.size(); i++) {
                videosIds.addAll(Playlist.getPlaylistItemsIds(response.get(i).getId()));
            }
            return videosIds;
        };

        Future<ArrayList<String>> submit = executor.submit(callable);
        Future<ArrayList<String>> submit2 = executor.submit(callable2);
        submit.get().addAll(submit2.get());
        return videosIds;
    }

    public static ArrayList<Video> getChannelVideos(ArrayList<String> videosIDs) throws ExecutionException, InterruptedException {
        ArrayList<Video> videos = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool (2);

        Callable<ArrayList<Video>> callable = () -> {
            ArrayList<Video> videos1 = new ArrayList<>();
            for (int i = 0; i < videosIDs.size() / 2; i++) {
                VideoResponse response = YouTubeClient.getVideo(videosIDs.get(i));
                assert response != null;
                if (response.getItems().size() != 0) {
                    videos1.add(response.getItems().get(0));
                }
            }
            return videos1;
        };

        Callable<ArrayList<Video>> callable2 = () -> {
            ArrayList<Video> videos2 = new ArrayList<>();
            for (int i = videosIDs.size() / 2; i < videosIDs.size(); i++) {
                VideoResponse response = YouTubeClient.getVideo(videosIDs.get(i));
                assert response != null;
                if (response.getItems().size() != 0) {
                    videos2.add(response.getItems().get(0));
                }
            }
            return videos2;
        };

        Future<ArrayList<Video>> submit = executor.submit(callable);
        Future<ArrayList<Video>> submit2 = executor.submit(callable2);
        videos.addAll(submit.get());
        videos.addAll(submit2.get());
        return videos;
    }
}
