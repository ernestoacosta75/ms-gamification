package microservices.book.gamification.infrastructure.adapter.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservices.book.gamification.domain.model.BadgeType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadgeCardEntity {
    @Id
    @GeneratedValue
    private Long badgeId;

    private Long userId;

    @EqualsAndHashCode.Exclude
    private long badgeTimestamp;

    private BadgeType badgeType;

    public BadgeCardEntity(final Long userId, final BadgeType badgeType) {
        this(null, userId, System.currentTimeMillis(), badgeType);
    }
}
