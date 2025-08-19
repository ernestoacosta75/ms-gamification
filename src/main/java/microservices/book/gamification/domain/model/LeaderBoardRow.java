package microservices.book.gamification.domain.model;

import java.util.List;

public record LeaderBoardRow(Long userId, Long totalScore, List<String> badges) {

    public LeaderBoardRow {
        if (userId == null || totalScore == null) {
            throw new IllegalArgumentException("User ID, total score cannot be null");
        }
        if (badges == null) {
            badges = List.of();
        }
    }

    public LeaderBoardRow(Long userId, Long totalScore) {
        this(userId, totalScore, List.of());
    }

    @Override
    public String toString() {
        return "LeaderBoardRow{" +
                "userId=" + userId +
                ", totalScore=" + totalScore +
                ", badges=" + badges +
                '}';
    }
}
