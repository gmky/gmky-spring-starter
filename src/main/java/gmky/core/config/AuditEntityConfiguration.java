package gmky.core.config;

import gmky.core.GmkyCoreConfiguration;
import gmky.core.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Slf4j
@Configuration
@EnableJpaAuditing
@ConditionalOnBean(GmkyCoreConfiguration.class)
public class AuditEntityConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "gmky.auditor.enabled", havingValue = "true", matchIfMissing = true)
    public AuditorAware<String> auditorProvider() {
        log.info("Configuring Auditor Provider");
        return () -> Optional.ofNullable(SecurityUtil.getCurrentUsername());
    }
}
