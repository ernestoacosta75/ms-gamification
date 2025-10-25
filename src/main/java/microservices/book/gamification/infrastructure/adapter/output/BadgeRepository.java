package microservices.book.gamification.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.infrastructure.adapter.output.crud.IBadgeCrudRepository;
import microservices.book.gamification.infrastructure.adapter.output.entity.BadgeCardEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BadgeRepository implements IBadgeRepository {
    private final IBadgeCrudRepository badgeCrudRepository;

    @Override
    public List<BadgeCardEntity> findByUserIdOrderByBadgeTimestampDesc(long userId) {

        return badgeCrudRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
    }

    @Override
    public void saveAll(List<BadgeCardEntity> badgeCards) {
        badgeCrudRepository.saveAll(badgeCards);
    }
}
