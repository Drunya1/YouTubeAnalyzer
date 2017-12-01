import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import entities.YouTubeItem;
import entities.YouTubeResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class YouTubeClient {

    private static final String YOU_TUBE = "https://www.googleapis.com/youtube/v3/{method}";

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private YouTubeClient(){}

    public static HttpResponse<YouTubeResponse> getChannelInfo(String idChannel) {
        try {
            String API_KEY = new Scanner(new File("src\\main\\resources\\YouTubeApiKey.txt")).next();
            return Unirest.get(YOU_TUBE)
                    .routeParam("method", "channels")
                    .queryString("id", idChannel)
                    .queryString("part", "snippet,statistics")
                    .queryString("key", API_KEY)
                    .asObject(YouTubeResponse.class);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        HttpResponse<YouTubeResponse> response = YouTubeClient.getChannelInfo("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        YouTubeResponse body = response.getBody();

        for (YouTubeItem item : body.items) {
            String date = item.snippet.publishedAt;
            System.out.println("             id: " + item.id);
            System.out.println("          title: " + item.snippet.title);
            System.out.println("    publishedAt: " + date.substring(0, 4) + "/" + date.substring(5, 7) + "/"
                    + date.substring(8, 10));
            System.out.println("   commentCount: " + item.statistics.commentCount);
            System.out.println("subscriberCount: " + item.statistics.subscriberCount);
            System.out.println("      viewCount: " + item.statistics.viewCount);
            System.out.println("     videoCount: " + item.statistics.videoCount);
        }
    }
}
