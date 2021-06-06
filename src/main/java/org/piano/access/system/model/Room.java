package org.piano.access.system.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.piano.access.system.exception.InvalidDataException;

@RequiredArgsConstructor
@Slf4j
public enum Room {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    @Getter
    private final int id;

    public static Room getById(int id) {
        for(Room room: Room.values()) {
            if (room.id == id) {
                return room;
            }
        }
        log.error("Id room is invalid: {}", id);
        throw new InvalidDataException(id);
    }
}
