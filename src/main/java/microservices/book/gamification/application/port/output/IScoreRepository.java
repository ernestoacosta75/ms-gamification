package microservices.book.gamification.application.port.output;

import microservices.book.gamification.domain.model.LeaderBoardRow;
import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoreCardEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IScoreRepository {
    List<ScoreCardEntity> findByUserIdOrderByBadgeTimestampDesc(long userId);
    void save(ScoreCard scoredCard);
    Optional<Integer> getTotalScoreForUser(@Param("userId") long userId);
    List<LeaderBoardRow> findFirst10();
}
