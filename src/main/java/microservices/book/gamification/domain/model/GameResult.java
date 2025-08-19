package microservices.book.gamification.domain.model;

import java.util.List;
import java.util.Objects;

public class GameResult {
    private final int score;
    private final List<BadgeType> badges;

    public GameResult(int score, List<BadgeType> badges) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative");
        }
        this.score = score;
        this.badges = badges != null ? badges : List.of();
    }

    public int getScore() {
        return score;
    }

    public List<BadgeType> getBadges() {
        return badges;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return score == that.score && Objects.equals(badges, that.badges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, badges);
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "score=" + score +
                ", badges=" + badges +
                '}';
    }
}
