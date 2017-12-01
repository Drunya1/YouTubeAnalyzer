package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.YouTubeItem;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeResponse {
    public ArrayList<YouTubeItem> items = new ArrayList<>();
}
