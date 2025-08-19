package microservices.book.gamification.application.port.input;

import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.domain.model.BadgeType;
import microservices.book.gamification.domain.model.ScoreCard;

import java.util.List;
import java.util.Optional;

/**
 * This interface accepts some contextual data and the solved attempt,
 * and decides whether a badge should be awarded or not.
 */
public interface IBadgeProcessor {

    /**
     * Processes some or all of the passed parameters and decides if the user
     * is entitled to a badge.
     * @return a {@link BadgeType} if the user is entitled to a badge, otherwise empty
     */
    Optional<BadgeType> processForOptionalBadge(
            int currentScore,
            List<ScoreCard> scoreCardList,
            ChallengeSolvedDto solved);

    /**
     * @return the {@link BadgeType} that this processor is handling. IT can be used
     * to filter processors according to our needs.
     */
    BadgeType badgeType();
}
