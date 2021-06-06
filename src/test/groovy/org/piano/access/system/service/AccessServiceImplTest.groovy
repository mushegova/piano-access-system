package org.piano.access.system.service

import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.piano.access.system.exception.ForbiddenException
import org.piano.access.system.exception.InvalidDataException
import org.piano.access.system.model.Room
import spock.lang.Specification
import spock.lang.Unroll
import org.piano.access.system.repository.UserRepository

import static org.mockito.ArgumentMatchers.anyInt
import static org.mockito.Mockito.when

class AccessServiceImplTest extends Specification {
    @Mock
    UserRepository repository

    AccessService service

    def setup() {
        MockitoAnnotations.openMocks(this)
        service = new AccessServiceImpl(repository)
    }

    def "Should complete successfully with new key and entrance is true"() {
        given:
        when(repository.isContainsKey(anyInt())).thenReturn(false)

        when:
        service.checkAccess(Room.ONE, true, 1)

        then:
        noExceptionThrown()
    }

    def "Should successfully leave the room"() {
        given:
        def room = Room.ONE
        when(repository.isContainsKey(anyInt())).thenReturn(true)
        when(repository.getRoomByKey(anyInt())).thenReturn(room)

        when:
        service.checkAccess(room, false, 1)

        then:
        noExceptionThrown()
    }

    @Unroll
    def "Should throw ForbiddenException"() {
        given:
        when(repository.isContainsKey(anyInt())).thenReturn(true)
        when(repository.getRoomByKey(anyInt())).thenReturn(Room.ONE)

        when:
        service.checkAccess(Room.THREE, entrance, key)

        then:
        thrown(ForbiddenException)

        where:
        key | isContainsKey | entrance
        3   | true          | true
        1   | false         | true
        3   | true          | false
    }

    @Unroll
    def "Should throw InvalidDataException"() {
        given:
        when(repository.isContainsKey(anyInt())).thenReturn(false)

        when:
        service.checkAccess(Room.ONE, true, key)

        then:
        thrown(InvalidDataException)

        where:
        key << [-1, 100000]
    }
}
