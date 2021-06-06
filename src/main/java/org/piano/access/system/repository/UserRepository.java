package org.piano.access.system.repository;

import org.piano.access.system.model.Room;

public interface UserRepository {

    void save(int key, Room room);
    void delete(int key);
    Room getRoomByKey(int key);
    boolean isContainsKey(int key);
}
