package microservices.book.gamification.infrastructure.adapter.mapper;

import microservices.book.gamification.domain.model.BadgeCardAggregate;
import microservices.book.gamification.infrastructure.adapter.output.entity.BadgeCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBadgeCardEntityMapper {
    IBadgeCardEntityMapper MAPPER = Mappers.getMapper(IBadgeCardEntityMapper.class);

//    @Mapping(target = "badgeId", ignore = true)
//    @Mapping(target = "badgeTimestamp", expression = "java(System.currentTimeMillis())")
//    BadgeCardAggregate map(BadgeCardEntity badgeCardEntity);

    List<BadgeCardEntity> map(List<BadgeCardAggregate> badgeCardAggregates);

    // Custom mapping method to use the create method from the Aggregate
    default BadgeCardAggregate mapToBadgeCardAggregate(BadgeCardEntity badgeCardEntity) {
        if (badgeCardEntity == null) {
            return null;
        }
        return BadgeCardAggregate.create(badgeCardEntity.getUserId(), badgeCardEntity.getBadgeType());
    }
}
