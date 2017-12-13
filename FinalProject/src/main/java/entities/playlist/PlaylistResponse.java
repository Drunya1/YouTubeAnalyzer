package entities.playlist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaylistResponse {
    private String nextPageToken;
    private ArrayList<Playlist> items = new ArrayList<>();

}
