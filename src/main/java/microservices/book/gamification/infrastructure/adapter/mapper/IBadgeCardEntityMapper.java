package microservices.book.gamification.infrastructure.adapter.mapper;

import microservices.book.gamification.domain.model.BadgeCardAggregate;
import microservices.book.gamification.infrastructure.adapter.output.entity.BadgeCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBadgeCardEntityMapper {
    IBadgeCardEntityMapper MAPPER = Mappers.getMapper(IBadgeCardEntityMapper.class);
    BadgeCardAggregate map(BadgeCardEntity badgeCardEntity);
    List<BadgeCardEntity> map(List<BadgeCardAggregate> badgeCardAggregates);
}
