package microservices.book.gamification.infrastructure.adapter.input;

import microservices.book.gamification.application.port.input.IBadgeProcessor;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.application.port.output.IScoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {
    private GameService gameService;

    @Mock
    private IScoreRepository scoreRepository;

    @Mock
    private IBadgeRepository badgeRepository;

    @Mock
    private IBadgeProcessor badgeProcessor;

    @BeforeEach
    public void setUp() {
        gameService = new GameService(scoreRepository, badgeRepository, List.of(badgeProcessor));
    }

}