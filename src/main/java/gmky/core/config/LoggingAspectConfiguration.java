package gmky.core.config;

import gmky.core.GmkyCoreConfiguration;
import gmky.core.aop.logging.LoggingAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnBean(GmkyCoreConfiguration.class)
public class LoggingAspectConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "gmky.logging.aspect", havingValue = "true", matchIfMissing = true)
    public LoggingAspect loggingAspect() {
        log.info("Configuring logging aspect");
        return new LoggingAspect();
    }
}
