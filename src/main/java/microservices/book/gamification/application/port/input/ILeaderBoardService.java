package microservices.book.gamification.application.port.input;

import microservices.book.gamification.domain.model.LeaderBoardRow;

import java.util.List;

public interface ILeaderBoardService {
    /**
     * Retrieves the current leader board, which is a list of users and their scores,
     * sorted in descending order by score.
     *
     * @return A list of LeaderBoardRow objects representing the current leader board.
     */
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
