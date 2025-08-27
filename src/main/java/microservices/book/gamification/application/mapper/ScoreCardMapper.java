package microservices.book.gamification.application.mapper;

import microservices.book.gamification.application.dto.ChallengeSolvedDto;
import microservices.book.gamification.domain.model.ScoreCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScoreCardMapper {
    ScoreCardMapper MAPPER = Mappers.getMapper(ScoreCardMapper.class);
    ScoreCard map(ChallengeSolvedDto challengeSolvedDto);
}
