package microservices.book.gamification.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.application.port.input.ILeaderBoardService;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.domain.model.LeaderBoardRow;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaderBoardService implements ILeaderBoardService {
    private final IScoreRepository scoreRepository;
    private final IBadgeRepository badgeRepository;

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        // Getting score only
        List<LeaderBoardRow> scoreOnly = scoreRepository.findFirst10();

        // Combining with badges
        var x = scoreOnly
                .stream()
                .map(row -> {
                    var badges = badgeRepository.findByUserIdOrderByBadgeTimestampDesc(row.getUserId())
                            .stream()
                            .map(b -> b.getBadgeType().getDescription())
                            .toList();
                    return row.withBadges(badges);
                }).toList();

        return x;
    }
}
