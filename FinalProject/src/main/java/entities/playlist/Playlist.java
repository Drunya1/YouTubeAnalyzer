package entities.playlist;

import client.YouTubeClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entities.Snippet;
import entities.playlistItems.PlaylistItem;
import entities.playlistItems.PlaylistItemsResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {
    private String id;
    private Snippet snippet;
    private ContentDetails contentDetails;

    public static ArrayList<String> getPlaylistItemsIds(String playlistId){
        PlaylistItemsResponse response = YouTubeClient.getPlaylistItems(playlistId, null);
        assert response != null;
        ArrayList<PlaylistItem> items = response.getItems();
        String nextPageToken = response.getNextPageToken();

        while (nextPageToken != null){
            response = YouTubeClient.getPlaylistItems(playlistId, nextPageToken);
            assert response != null;
            items.addAll(response.getItems());
            nextPageToken = response.getNextPageToken();
        }

        ArrayList<String> itemsIds = new ArrayList<>();
        for (PlaylistItem item: items){
            itemsIds.add(item.getContentDetails().getVideoId());
        }
        return itemsIds;
    }
}
