package gmky.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@PropertySource("classpath:/gmky-starter.yaml")
@ConfigurationProperties(prefix = "gmky")
public class GmkyCoreProperties {
    private boolean enabled;
    private Swagger swagger;
    private Auditor auditor;
    private Logging logging;
    private Security security;
    private Liquibase liquibase;

    @Getter
    @Setter
    public static class Swagger {
        private Jwt jwt;

        @Getter
        @Setter
        public static class Jwt {
            private boolean enabled;
        }
    }

    @Getter
    @Setter
    public static class Auditor {
        private boolean enabled;
    }

    @Getter
    @Setter
    public static class Logging {
        private boolean aspect;
    }

    @Getter
    @Setter
    public static class Security {
        private boolean enabled;
        private List<String> publicPaths = new ArrayList<>();
        private String secret;
        private Long expiration;
    }

    @Getter
    @Setter
    public static class Liquibase {
        private boolean enabled;
        private String changeLog;
    }


}
