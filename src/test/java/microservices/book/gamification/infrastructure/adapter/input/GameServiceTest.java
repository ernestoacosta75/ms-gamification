package microservices.book.gamification.infrastructure.adapter.input;

import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.application.port.input.IBadgeProcessor;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.domain.model.BadgeType;
import microservices.book.gamification.domain.model.GameResult;
import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.output.entity.BadgeCardEntity;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoredCardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    private GameService gameService;

    @Mock
    private IScoreRepository scoreRepository;

    @Mock
    private IBadgeRepository badgeRepository;

    @Mock
    private IBadgeProcessor badgeProcessor;

    @BeforeEach
    public void setUp() {
        gameService = new GameService(scoreRepository, badgeRepository, List.of(badgeProcessor));
    }

    @Test
    public void processCorrectAttemptTest() {
        // given
        long userId = 1L, attemptId = 1L, badgeId = 1L;
        int score = 10;
        var attempt = new ChallengeSolvedDto(attemptId, true, 20, 70, userId, "john");
        ScoreCard scoreCard = new ScoreCard(userId, attempt.getAttemptId());
        ScoredCardEntity scoreCardEntity = new ScoredCardEntity(userId, attempt.getAttemptId(), score);

        given(scoreRepository.getTotalScoreForUser(userId))
                .willReturn(Optional.of(10));

        given(scoreRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
                .willReturn(List.of(scoreCardEntity));

        given(badgeRepository.findByUserIdOrderByBadgeTimestampDesc(userId))
                .willReturn(List.of(new BadgeCardEntity(userId, BadgeType.FIRST_WON)));

        given(badgeProcessor.badgeType()).willReturn(BadgeType.LUCKY_NUMBER);
        given(badgeProcessor.processForOptionalBadge(10, List.of(scoreCard), attempt))
                .willReturn(Optional.of(BadgeType.LUCKY_NUMBER));

        // when
        final GameResult gameResult = gameService.newAttemptForUser(attempt);

        // then
        then(gameResult).isEqualTo(
                new GameResult(10, List.of(BadgeType.LUCKY_NUMBER))
        );

        // Verifying the calls to the repositories methods
        verify(scoreRepository).save(scoreCard);
        verify(badgeRepository).saveAll(List.of(new BadgeCardEntity(userId, BadgeType.LUCKY_NUMBER)));
    }

}