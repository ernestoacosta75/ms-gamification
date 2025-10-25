package microservices.book.gamification.infrastructure.adapter.output.crud;

import microservices.book.gamification.infrastructure.adapter.output.entity.BadgeCardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBadgeCrudRepository extends CrudRepository<BadgeCardEntity, Long> {
    List<BadgeCardEntity> findByUserIdOrderByBadgeTimestampDesc(long userId);
}
