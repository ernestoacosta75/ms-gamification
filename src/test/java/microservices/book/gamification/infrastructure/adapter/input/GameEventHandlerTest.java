package microservices.book.gamification.infrastructure.adapter.input;

import microservices.book.gamification.application.dto.ChallengeSolvedEvent;
import microservices.book.gamification.application.port.input.IGameService;
import microservices.book.gamification.domain.model.BadgeType;
import microservices.book.gamification.domain.model.GameResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class GameEventHandlerTest {

    private GameEventHandler gameEventHandler;

    @Mock
    private IGameService gameService;

    private ChallengeSolvedEvent event;

    @BeforeEach
    void setUp() {
        gameEventHandler = new GameEventHandler(gameService);
        event = new ChallengeSolvedEvent(1L, true,
                50, 60, 42L, "john_doe");
    }

    @Test
    void givenValidEvent_whenHandle_thenGameServiceIsCalled() {
        // Given
        final GameResult expectedResult = new GameResult(10, List.of(BadgeType.FIRST_WON));
        given(gameService.newAttemptForUser(event)).willReturn(expectedResult);

        // When
        gameEventHandler.handleMultiplicationSolved(event);

        // Then
        then(gameService).should(times(1)).newAttemptForUser(event);
    }

    @Test
    void givenServiceThrowsException_whenHandle_thenRejectExceptionIsThrown() {
        // Given
        final IllegalStateException domainException = new IllegalStateException("Domain rule violation");
        given(gameService.newAttemptForUser(event)).willThrow(domainException);

        // When
        final AmqpRejectAndDontRequeueException amqpException = assertThrows(
                AmqpRejectAndDontRequeueException.class,
                () -> gameEventHandler.handleMultiplicationSolved(event),
                "AmqpRejectAndDontRequeueException expected");

        // Then
        then(gameService).should(times(1)).newAttemptForUser(event);
        assertSame(domainException,
                amqpException.getCause(),
                "Cause of the exception should be the original domain exception");
    }
}