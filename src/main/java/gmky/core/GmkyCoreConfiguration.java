package gmky.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GmkyCoreProperties.class)
@ConditionalOnProperty(name = "gmky.enabled", havingValue = "true", matchIfMissing = true)
public class GmkyCoreConfiguration {
}
