package microservices.book.gamification.infrastructure.adapter.output.crud;

import microservices.book.gamification.infrastructure.adapter.output.entity.ScoredCardEntity;
import org.springframework.data.repository.CrudRepository;

public interface IScoreCardCrudRepository  extends CrudRepository<ScoredCardEntity, Long> {
}
