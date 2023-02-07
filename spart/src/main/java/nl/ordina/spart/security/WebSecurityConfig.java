package nl.ordina.spart.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.cors(
                cors -> cors.configurationSource(
                        request -> {
                            var corsConfiguration = new CorsConfiguration();
                            corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:*"));
                            corsConfiguration.setAllowedMethods
                                    (List.of("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH"));
                            corsConfiguration.setAllowCredentials(true);
                            return corsConfiguration;
                        }
                )
        );
        return http.build();
    }
}
