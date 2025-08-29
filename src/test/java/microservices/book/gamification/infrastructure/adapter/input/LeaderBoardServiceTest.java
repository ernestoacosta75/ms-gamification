package microservices.book.gamification.infrastructure.adapter.input;

import microservices.book.gamification.application.port.input.ILeaderBoardService;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.domain.model.BadgeType;
import microservices.book.gamification.domain.model.LeaderBoardRow;
import microservices.book.gamification.infrastructure.adapter.output.entity.BadgeCardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class LeaderBoardServiceTest {
    private ILeaderBoardService leaderBoardService;

    @Mock
    private IScoreRepository scoreRepository;

    @Mock
    private IBadgeRepository badgeRepository;

    @BeforeEach
    public void setUp() {
        leaderBoardService = new LeaderBoardService(scoreRepository, badgeRepository);
    }

    @Test
    public void retrieveLeaderBoardTest() {
        // given
        LeaderBoardRow scoreRow = new LeaderBoardRow(1L, 300L, List.of());
        given(scoreRepository.findFirst10()).willReturn(List.of(scoreRow));
        given(badgeRepository.findByUserIdOrderByBadgeTimestampDesc(1L))
                .willReturn(List.of(new BadgeCardEntity(1L, BadgeType.LUCKY_NUMBER)));

        // when
        var leaderBoard = leaderBoardService.getCurrentLeaderBoard();

        // then
        List<LeaderBoardRow> expectedLeaderBoard = List.of(
                new LeaderBoardRow(1L, 300L, List.of(BadgeType.LUCKY_NUMBER.getDescription()))
        );

        then(leaderBoard).equals(expectedLeaderBoard);
    }
}