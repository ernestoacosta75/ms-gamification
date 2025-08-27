package microservices.book.gamification.application.port.output;

import microservices.book.gamification.infrastructure.adapter.output.entity.BadgeCardEntity;

import java.util.List;

public interface IBadgeRepository {
    List<BadgeCardEntity> findByUserIdOrderByBadgeTimestampDesc(long userId);
    void saveAll(List<BadgeCardEntity> badgeCards);
}
