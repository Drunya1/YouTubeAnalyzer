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
}
