package microservices.book.gamification.application.port.input;

import microservices.book.gamification.application.dto.ChallengeSolvedEvent;

public interface IGameEventHandler {
    void handleMultiplicationSolved(final ChallengeSolvedEvent event);
}
