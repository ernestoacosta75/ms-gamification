package microservices.book.gamification.infrastructure.adapter.config;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import lombok.Setter;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import microservices.book.gamification.application.port.output.IScoreRepository;
import microservices.book.gamification.infrastructure.adapter.output.BadgeRepository;
import microservices.book.gamification.infrastructure.adapter.output.ScoreRepository;
import microservices.book.gamification.infrastructure.adapter.output.crud.IBadgeCrudRepository;
import microservices.book.gamification.infrastructure.adapter.output.crud.IScoreCardCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppConfig {
    @Autowired
    private IScoreCardCrudRepository scoreCardCrudRepository;

    @Autowired
    private IBadgeCrudRepository badgeCrudRepository;

    private IScoreRepository scoreRepository() {
        return new ScoreRepository(scoreCardCrudRepository);
    }

    private IBadgeRepository badgeRepository() {
        return new BadgeRepository(badgeCrudRepository);
    }

    @Bean
    public Hibernate6Module hibernate6Module() {
        return new Hibernate6Module();
    }
}
