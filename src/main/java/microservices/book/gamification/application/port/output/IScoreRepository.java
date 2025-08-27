package microservices.book.gamification.application.port.output;

import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoredCardEntity;

import java.util.List;

public interface IScoreRepository {
    List<ScoredCardEntity> findByUserIdOrderByBadgeTimestampDesc(long userId);
    void save(ScoreCard scoredCard);
}
