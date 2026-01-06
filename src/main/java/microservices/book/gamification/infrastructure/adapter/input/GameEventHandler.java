package microservices.book.gamification.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.application.dto.ChallengeSolvedEvent;
import microservices.book.gamification.application.port.input.IGameEventHandler;
import microservices.book.gamification.application.port.input.IGameService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class GameEventHandler implements IGameEventHandler {
    private final IGameService gameService;

    @Override
    @RabbitListener(queues = "${amqp.queue.gamification}")
    public void handleMultiplicationSolved(ChallengeSolvedEvent event) {
        log.info("Challenge Solved Event received: {}", event.getAttemptId());

        try {
            gameService.newAttemptForUser(event);
        } catch (final Exception e) {
            log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoiding the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
