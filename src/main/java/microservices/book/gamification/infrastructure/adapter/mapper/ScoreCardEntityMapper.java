package microservices.book.gamification.infrastructure.adapter.mapper;

import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoredCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScoreCardEntityMapper {
    ScoreCardEntityMapper MAPPER = Mappers.getMapper(ScoreCardEntityMapper.class);
    ScoredCardEntity map(ScoreCard scoreCard);
    ScoreCard map(ScoredCardEntity scoredCardEntity);
}
