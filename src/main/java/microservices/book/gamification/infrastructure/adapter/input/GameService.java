package microservices.book.gamification.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.application.port.input.IBadgeProcessor;
import microservices.book.gamification.application.port.input.IGameService;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.domain.model.GameResult;
import microservices.book.gamification.domain.model.ScoreCard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService implements IGameService {
    private final IScoreRepository scoreRepository;
    private final IBadgeRepository badgeRepository;

    // Spring will injects all the @Component beans  in this list
    private final List<IBadgeProcessor> badgeProcessors;

    @Override
    public GameResult newAttemptForUser(ChallengeSolvedDto challenge) {
        // Giving points only if it's correct
        if (challenge.isCorrect()) {
            ScoreCard scoreCard = new ScoreCard(challenge.getUserId(), challenge.getAttemptId());
        }
        return null;
    }
}
