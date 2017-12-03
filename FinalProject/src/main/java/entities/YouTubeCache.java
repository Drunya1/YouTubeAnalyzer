package entities;

import com.mashape.unirest.http.HttpResponse;

import java.util.ArrayList;

public class YouTubeCache {
    private ArrayList<YouTubeResponse> responses = new ArrayList<YouTubeResponse>();

    public ArrayList<YouTubeResponse> getResponses() {
        return responses;
    }

    public void setResponses(ArrayList<YouTubeResponse> responses) {
        this.responses = responses;
    }
}
