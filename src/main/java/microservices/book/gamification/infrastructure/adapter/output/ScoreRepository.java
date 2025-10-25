package microservices.book.gamification.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.domain.model.LeaderBoardRow;
import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.mapper.ScoreCardEntityMapper;
import microservices.book.gamification.infrastructure.adapter.output.crud.IScoreCardCrudRepository;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoreCardEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScoreRepository implements IScoreRepository {

    private final IScoreCardCrudRepository scoreCardCrudRepository;

    @Override
    public List<ScoreCardEntity> findByUserIdOrderByBadgeTimestampDesc(long userId) {

        return scoreCardCrudRepository.findByUserIdOrderByScoreTimestampDesc(userId);
    }

    @Override
    public void save(ScoreCard scoreCard) {

        scoreCardCrudRepository.save(ScoreCardEntityMapper.MAPPER.map(scoreCard));
    }

    @Override
    public Optional<Integer> getTotalScoreForUser(long userId) {
        return scoreCardCrudRepository.getTotalScoreForUser(userId);
    }

    @Override
    public List<LeaderBoardRow> findFirst10() {
        return scoreCardCrudRepository.findFirst10();
    }
}
