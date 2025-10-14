package microservices.book.gamification.infrastructure.adapter.mapper;

import microservices.book.gamification.domain.model.ScoreCard;
import microservices.book.gamification.infrastructure.adapter.output.entity.ScoreCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScoreCardEntityMapper {
    ScoreCardEntityMapper MAPPER = Mappers.getMapper(ScoreCardEntityMapper.class);
    ScoreCardEntity map(ScoreCard scoreCard);
    ScoreCard map(ScoreCardEntity scoreCardEntity);
}
