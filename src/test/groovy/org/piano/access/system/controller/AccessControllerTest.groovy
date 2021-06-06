package org.piano.access.system.controller

import org.mockito.Mockito
import org.piano.access.system.model.Room
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import org.piano.access.system.service.AccessService

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AccessController)
class AccessControllerTest extends Specification {
    @Autowired
    MockMvc mockMvc

    @MockBean
    AccessService service

    def "Should return response 200"() {
        expect:
        mockMvc.perform(post("/check?roomId=1&entrance=true&keyId=1"))
                .andExpect(status().isOk())

        Mockito.verify(service).checkAccess(Room.ONE, true, 1)
    }

    def "Should return response 403"() {
        expect:
        mockMvc.perform(post("/check?roomId=9&entrance=true&keyId=1"))
                .andExpect(status().isForbidden())
    }
}
