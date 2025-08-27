package microservices.book.gamification.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.mapper.ScoreCardEntityMapper;
import microservices.book.gamification.infrastructure.adapter.output.crud.IScoreCardCrudRepository;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoredCardEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScoreRepository implements IScoreRepository {

    private final IScoreCardCrudRepository scoreCardCrudRepository;

    @Override
    public List<ScoredCardEntity> findByUserIdOrderByBadgeTimestampDesc(long userId) {
        return List.of();
    }

    @Override
    public void save(ScoreCard scoreCard) {
        scoreCardCrudRepository.save(ScoreCardEntityMapper.MAPPER.map(scoreCard));
    }
}
