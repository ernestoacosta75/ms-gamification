package microservices.book.gamification.infrastructure.adapter.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class represents the Score linked to an attempt in the game,
 * with an associated user and the timestamp in which the score is registered.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoredCardEntity {
    @Id
    @GeneratedValue
    private Long cardId;
    private Long userId;
    private Long attemptId;

    @EqualsAndHashCode.Exclude
    private long scoreTimestamp;
    private int score;

    public ScoredCardEntity(final Long userId, final Long attemptId, final int score) {
        this(null, userId, attemptId, System.currentTimeMillis(), score);
    }
}
