package microservices.book.gamification.application.rest;

import lombok.RequiredArgsConstructor;
import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.application.port.input.IGameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
public class GameController {
    private final IGameService gameService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    void postResult(@RequestBody ChallengeSolvedDto challengeSolvedDto) {
        gameService.newAttemptForUser(challengeSolvedDto);
    }
}
