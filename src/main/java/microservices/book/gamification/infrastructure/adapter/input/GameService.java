package microservices.book.gamification.infrastructure.adapter.input;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.application.port.input.IBadgeProcessor;
import microservices.book.gamification.application.port.input.IGameService;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.domain.model.BadgeCardAggregate;
import microservices.book.gamification.domain.model.BadgeType;
import microservices.book.gamification.domain.model.GameResult;
import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.mapper.IBadgeCardEntityMapper;
import microservices.book.gamification.infrastructure.adapter.mapper.ScoreCardEntityMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameService implements IGameService {
    private final IScoreRepository scoreRepository;
    private final IBadgeRepository badgeRepository;

    // Spring will inject all the @Component beans  in this list
    private final List<IBadgeProcessor> badgeProcessors;

    @Override
    public GameResult newAttemptForUser(ChallengeSolvedDto challenge) {
        // Giving points only if it's correct
        if (challenge.isCorrect()) {
            ScoreCard scoreCard = new ScoreCard(challenge.getUserId(), challenge.getAttemptId());
            scoreRepository.save(scoreCard);

            log.info("User {} scored {} points for attempt id {}",
                    challenge.getUserAlias(), scoreCard.getScore(), challenge.getAttemptId());


        }

        return null;
    }

    /**
     * Checks the total score and the different socre cards obtained
     * to give new badges in case their conditions are met.
     */
    private List<BadgeCardAggregate> processForBadges(final ChallengeSolvedDto challengeSolvedDto) {
        Optional<Integer> optTotalScore = scoreRepository
                .getTotalScoreForUser(challengeSolvedDto.getUserId());

        if(optTotalScore.isEmpty()) {
            return Collections.emptyList();
        }

        int totalScore = optTotalScore.get();

        // Getting the total score and existing badges for that user
        List<ScoreCard> scoreCardList = scoreRepository
                .findByUserIdOrderByBadgeTimestampDesc(challengeSolvedDto.getUserId())
                .stream()
                .map(ScoreCardEntityMapper.MAPPER::map)
                .toList();

        Set<BadgeType> alreadyGotBadges = badgeRepository
                .findByUserIdOrderByBadgeTimestampDesc(challengeSolvedDto.getUserId())
                .stream()
                .map(IBadgeCardEntityMapper.MAPPER::map)
                .map(BadgeCardAggregate::getBadgeType)
                .collect(Collectors.toSet());

        // Calling the badge processors for badges that the user doesn't have yet
        List<BadgeCardAggregate> newBadgeCards = badgeProcessors
                .stream()
                .filter(bp -> !alreadyGotBadges.contains(bp.badgeType()))
                .map(bp -> bp.processForOptionalBadge(totalScore, scoreCardList, challengeSolvedDto))
                .flatMap(Optional::stream) // returns an empty stream if empty
                // mapping the optionals if present to new BadgeCardAggregate
                .map(badgeType ->
                        BadgeCardAggregate.create(challengeSolvedDto.getUserId(), badgeType))
                .toList();

        badgeRepository.saveAll(IBadgeCardEntityMapper.MAPPER.map(newBadgeCards));

        return newBadgeCards;
    }
}
