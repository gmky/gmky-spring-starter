package gmky.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EntityScan(basePackages = {"gmky.core.entity"})
@EnableConfigurationProperties(GmkyCoreProperties.class)
@ComponentScans({
        @ComponentScan(basePackages = {"gmky.core.web"}),
        @ComponentScan(basePackages = {"gmky.core.service"}),
        @ComponentScan(basePackages = {"gmky.core.mapper"})
})
@ConditionalOnProperty(name = "gmky.enabled", havingValue = "true", matchIfMissing = true)
public class GmkyCoreConfiguration {
}

