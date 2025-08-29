package microservices.book.gamification.infrastructure.adapter.input;

import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.application.port.input.IBadgeProcessor;
import microservices.book.gamification.domain.model.BadgeType;
import microservices.book.gamification.domain.model.ScoreCard;

import java.util.List;
import java.util.Optional;

public class LuckyNumberBadgeProcessor implements IBadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(int currentScore, List<ScoreCard> scoreCardList, ChallengeSolvedDto solved) {
        return (solved.getFactorA() == 42 || solved.getFactorB() == 42) ?
                Optional.of(BadgeType.LUCKY_NUMBER) : Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.LUCKY_NUMBER;
    }
}
