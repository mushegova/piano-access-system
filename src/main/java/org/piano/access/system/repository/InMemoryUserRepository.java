package org.piano.access.system.repository;

import org.piano.access.system.model.Room;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryUserRepository implements UserRepository {
    private final ConcurrentHashMap<Integer, Room> userRoomMap = new ConcurrentHashMap<>();

    @Override
    public void save(int key, Room room) {
        userRoomMap.put(key, room);
    }

    @Override
    public void delete(int key) {
        userRoomMap.remove(key);
    }

    @Override
    public Room getRoomByKey(int key) {
        return userRoomMap.get(key);
    }

    @Override
    public boolean isContainsKey(int key) {
        return userRoomMap.containsKey(key);
    }
}
