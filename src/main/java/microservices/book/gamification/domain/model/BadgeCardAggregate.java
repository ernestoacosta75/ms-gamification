package microservices.book.gamification.domain.model;

import java.util.Objects;

public class BadgeCardAggregate {
    private Long badgeId;
    private Long userId;
    private long badgeTimestamp;
    private BadgeType badgeType;

    private BadgeCardAggregate() {
    }

    private BadgeCardAggregate(Long userId, BadgeType badgeType) {
        this(null, userId, System.currentTimeMillis(), badgeType);
    }

    private BadgeCardAggregate(Long badgeId, Long userId, long badgeTimestamp, BadgeType badgeType) {
        this.badgeId = badgeId;
        this.userId = userId;
        this.badgeTimestamp = badgeTimestamp;
        this.badgeType = badgeType;
    }

    public static BadgeCardAggregate create(Long userId, BadgeType badgeType) {
        validateInput(userId, badgeType);
        return new BadgeCardAggregate(userId, badgeType);
    }

    public Long getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Long badgeId) {
        this.badgeId = badgeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getBadgeTimestamp() {
        return badgeTimestamp;
    }

    public void setBadgeTimestamp(long badgeTimestamp) {
        this.badgeTimestamp = badgeTimestamp;
    }

    public BadgeType getBadgeType() {
        return badgeType;
    }

    public void setBadgeType(BadgeType badgeType) {
        this.badgeType = badgeType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BadgeCardAggregate that = (BadgeCardAggregate) o;
        return Objects.equals(badgeId, that.badgeId) && Objects.equals(userId, that.userId) && badgeType == that.badgeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeId, userId, badgeType);
    }

    private static void validateInput(Long userId, BadgeType badgeType) {
        if (userId == null || badgeType == null) {
            throw new IllegalArgumentException("User ID and Badge Type must not be null");
        }
    }
}
