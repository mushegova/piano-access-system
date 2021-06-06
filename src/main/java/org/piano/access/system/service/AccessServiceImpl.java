package org.piano.access.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.piano.access.system.exception.ForbiddenException;
import org.piano.access.system.exception.InvalidDataException;
import org.piano.access.system.model.Room;
import org.springframework.stereotype.Service;
import org.piano.access.system.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {
    private final static int USER_COUNT = 10000;

    private final UserRepository repository;

    @Override
    public void checkAccess(Room room, boolean entrance, int key) {
        if (key <= 0 || key > USER_COUNT) {
            throw new InvalidDataException(key);
        }

        if (repository.isContainsKey(key) && entrance) {
            log.info("User {} cannot enter the room {}", key, room);
            throw new ForbiddenException(key);
        }

        if (!repository.isContainsKey(key) && entrance
                && key % room.getId() == 0) {
            repository.save(key, room);
            log.info("User {} entered the room {}", key, room);
            return;
        }

        if (repository.isContainsKey(key) && !entrance
                && repository.getRoomByKey(key) == room) {
            repository.delete(key);
            log.info("User {} left the room {}", key, room);
            return;
        }
        log.info("User {} cannot enter the room {}", key, room);
        throw new ForbiddenException(key);
    }

}
