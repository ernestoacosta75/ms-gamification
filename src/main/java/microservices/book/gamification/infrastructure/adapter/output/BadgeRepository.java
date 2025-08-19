package microservices.book.gamification.infrastructure.adapter.output;

import lombok.RequiredArgsConstructor;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BadgeRepository implements IBadgeRepository {
}
