import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import entities.YouTubeCache;
import entities.YouTubeResponse;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class YouTubeClient {

    private static final String YOU_TUBE = "https://www.googleapis.com/youtube/v3/{method}";
    private static final String CACHE_PATH = "src\\main\\resources\\cache\\cache.json";

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

    public static YouTubeResponse getChannelInfo(String idChannel) {
        try {
            YouTubeCache cache = null;
            Scanner scanner = new Scanner(new File(CACHE_PATH));
            if (scanner.hasNext()) {
                String json = scanner.useDelimiter("\\Z").next();
                cache = JSON.parseObject(json, YouTubeCache.class);
                for (int i = 0; i < cache.getResponses().size(); i++) {
                    if (cache.getResponses().get(i).getItems().get(0).getId().equals(idChannel)) {
                        return cache.getResponses().get(i);
                    }
                }
            }
            String API_KEY = new Scanner(new File("src\\main\\resources\\YouTubeApiKey.txt")).next();
            YouTubeResponse response = Unirest.get(YOU_TUBE)
                    .routeParam("method", "channels")
                    .queryString("id", idChannel)
                    .queryString("part", "snippet,statistics")
                    .queryString("key", API_KEY)
                    .asObject(YouTubeResponse.class).getBody();

            if (cache == null) {
                cache = new YouTubeCache();
            }
            cache.getResponses().add(response);
            FileWriter writer = new FileWriter(new File(CACHE_PATH));
            writer.write(JSON.toJSONString(cache));
            writer.flush();
            return response;
        } catch(UnirestException e){
            e.printStackTrace();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
