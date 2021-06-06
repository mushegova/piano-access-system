package org.piano.access.system.service;

import org.piano.access.system.model.Room;

public interface AccessService {

    void checkAccess(Room room, boolean entrance, int key);
}
