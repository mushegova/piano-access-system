package org.piano.access.system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.piano.access.system.model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.piano.access.system.service.AccessService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccessController {
    private final AccessService service;

    @PostMapping(value = "/check")
    public ResponseEntity<String> checkAccess(@RequestParam("roomId") int room,
                                              @RequestParam("entrance") boolean entrance,
                                              @RequestParam("keyId") int key) {
        log.info("Start process...for user {}, room {}, entrance {}", key, room, entrance);
        service.checkAccess(Room.getById(room), entrance, key);
        log.info("Completed process...for user {}, room {}, entrance {}", key, room, entrance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
