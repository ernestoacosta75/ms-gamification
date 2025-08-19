package microservices.book.gamification.application.port.input;

import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.domain.model.GameResult;

public interface IGameService {
    /**
     * Process a new attempt from a given user.
     *
     * @param challenge the challenge data with user details, factors, etc.
     * @return a {@link GameResult} object containing the new score and badge cards obtained
     */
    GameResult newAttemptForUser(ChallengeSolvedDto challenge);
}
