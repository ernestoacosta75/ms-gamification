package microservices.book.gamification.infrastructure.adapter.output.crud;

import microservices.book.gamification.domain.model.LeaderBoardRow;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoreCardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IScoreCardCrudRepository  extends CrudRepository<ScoreCardEntity, Long> {
    /**
     * Gets the total score for a given user: the sum of the scores of all
     * their ScoreCards.
     *
     * @param userId the id of the user
     * @return the total score for the user, empty if the user doesn't exist
     */
    @Query("SELECT COALESCE(SUM(s.score), 0) FROM ScoreCardEntity s WHERE s.userId = :userId GROUP BY s.userId")
    Optional<Integer> getTotalScoreForUser(@Param("userId") Long userId);

    /**
     * Retrieves a list of {@link LeaderBoardRow}s representing the Leader Board
     * of users and their total score.
     *
     * @return the leader board, sorted by highest score first.
     */
    @Query("SELECT NEW microservices.book.gamification.domain.model.LeaderBoardRow(s.userId, SUM(s.score)) " +
            "FROM ScoreCardEntity s " +
            "GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();

    /**
     * Retrieves all the ScoreCards for a given user, identified by his user id.
     *
     * @param userId the id of the user
     * @return a list containing all the ScoreCards for the given user,
     * sorted by most recent.
     */
    List<ScoreCardEntity> findByUserIdOrderByScoreTimestampDesc(final Long userId);
}
