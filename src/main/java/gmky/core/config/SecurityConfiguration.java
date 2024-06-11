package gmky.core.config;

import gmky.core.GmkyCoreConfiguration;
import gmky.core.GmkyCoreProperties;
import gmky.core.repository.UserRepository;
import gmky.core.security.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
@ConditionalOnBean(GmkyCoreConfiguration.class)
@ConditionalOnProperty(name = "gmky.security.enabled", havingValue = "true")
public class SecurityConfiguration {
    private final GmkyCoreProperties properties;
    private final UserRepository userRepository;

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        log.info("Initializing password encoder");
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Applying filter chain");
        var publicPaths = properties.getSecurity().getPublicPaths();
        return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(ssm -> ssm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(httpReq -> {
                    log.info("Applying authorize requests");
                    if (!publicPaths.isEmpty()) {
                        log.info("Applying public paths: {}", publicPaths);
                        publicPaths.forEach(item -> httpReq.requestMatchers(item).permitAll());
                    }
                    httpReq
                            .requestMatchers("/error").permitAll()
                            .requestMatchers("/v3/api-docs/**").permitAll()
                            .requestMatchers("/swagger-ui/**").permitAll()
                            .requestMatchers("/client-api/v1/auth/login").permitAll()
                            .requestMatchers("/client-api/v1/forgot-password").permitAll()
                            .anyRequest().authenticated();
                })
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationProvider authenticationProvider() {
        log.info("Initializing authentication provider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        log.info("Initializing authentication manager");
        return configuration.getAuthenticationManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtFilter jwtFilter() {
        log.info("Initializing JWT filter");
        return new JwtFilter(tokenProvider());
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenProvider tokenProvider() {
        return new TokenProviderImpl(userDetailsService());
    }

    @Bean
    @ConditionalOnMissingBean
    public MethodSecurityExpressionHandler expressionHandler() {
        return new CustomMethodSecurityExpressionHandler(userRepository);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }
}
