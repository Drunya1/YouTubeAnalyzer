package service;

import entities.channel.Channel;
import java.util.ArrayList;

public interface CollectionChannelsInfo {
    void add(ArrayList<Channel> channels);
    void addMediaResonance(ArrayList<Channel> channels);
    void removeAll();
}
