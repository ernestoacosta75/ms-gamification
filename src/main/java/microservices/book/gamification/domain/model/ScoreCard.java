package microservices.book.gamification.domain.model;

import java.util.Objects;

public class ScoreCard {
    public static final int DEFAULT_SCORE = 10;
    private Long cardId;
    private Long userId;
    private Long attemptId;
    private long scoreTimestamp;
    private int score;

    public ScoreCard() {

    }

    public ScoreCard(final Long userId, final Long attemptId) {
        this(null, userId, attemptId, System.currentTimeMillis(), DEFAULT_SCORE);
    }

    public ScoreCard(final Long cardId, final Long userId, final Long attemptId, final long scoreTimestamp, final int score) {
        this.cardId = cardId;
        this.userId = userId;
        this.attemptId = attemptId;
        this.scoreTimestamp = scoreTimestamp;
        this.score = score;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public long getScoreTimestamp() {
        return scoreTimestamp;
    }

    public void setScoreTimestamp(long scoreTimestamp) {
        this.scoreTimestamp = scoreTimestamp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScoreCard scoreCard = (ScoreCard) o;
        return score == scoreCard.score && Objects.equals(cardId, scoreCard.cardId) && Objects.equals(userId, scoreCard.userId) && Objects.equals(attemptId, scoreCard.attemptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, userId, attemptId, score);
    }
}
