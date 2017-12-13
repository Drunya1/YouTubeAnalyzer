package entities.playlistItems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entities.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistItemsResponse {
    private String nextPageToken;
    private PageInfo pageInfo;
    private ArrayList<PlaylistItem> items = new ArrayList<>();
}
