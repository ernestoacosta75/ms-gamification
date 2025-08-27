package microservices.book.gamification.infrastructure.adapter.config;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import lombok.Setter;
import microservices.book.gamification.application.port.output.IBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class AppConfig {
    @Autowired
    private IBadgeRepository badgeRepository;

    @Bean
    public Hibernate6Module hibernate6Module() {
        return new Hibernate6Module();
    }
	
    @Bean
    public NewTopic gamificationTopic() {
        return new TopicBuilder.name("gamification-challenges")
		           .partitions(3)
				   .compact()
				   .build();
    }
}
