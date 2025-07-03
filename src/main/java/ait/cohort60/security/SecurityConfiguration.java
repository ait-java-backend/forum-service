package ait.cohort60.security;

import ait.cohort60.accounting.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomWebSecurity webSecurity;

    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.cors(Customizer.withDefaults());
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/account/register", "/forum/posts/**")
                .permitAll()
                .requestMatchers("/account/user/{login}/role/{role}")
                .hasRole(Role.ADMINISTRATOR.name())
                .requestMatchers(HttpMethod.PATCH, "/account/user/{login}")
                .access(new WebExpressionAuthorizationManager("#login == authentication.name"))
                .requestMatchers(HttpMethod.DELETE, "/account/user/{login}")
                .access(new WebExpressionAuthorizationManager("#login == authentication.name or hasRole('ADMINISTRATOR')"))
                .requestMatchers(HttpMethod.POST, "/forum/posts/{author}")
                .access(new WebExpressionAuthorizationManager("#author == authentication.name"))
                .requestMatchers(HttpMethod.PATCH, "/forum/post/{id}/comment/{author}")
                .access(new WebExpressionAuthorizationManager("#author == authentication.name"))
                .requestMatchers(HttpMethod.PATCH, "/forum/post/{id}")
                .access(((authentication, context) ->
                        new AuthorizationDecision(webSecurity.checkPostAuthor(context.getVariables().get("id"), authentication.get().getName()))))
                .requestMatchers(HttpMethod.DELETE, "/forum/post/{id}")
                .access((authentication, context) -> {
                    boolean isAuthor = webSecurity.checkPostAuthor(context.getVariables().get("id"),
                            authentication.get().getName());
                    boolean isModerator = context.getRequest().isUserInRole(Role.MODERATOR.name());
                    return new AuthorizationDecision(isAuthor || isModerator);
                })

                .anyRequest()
                .authenticated());
        return http.build();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Указывается реальный адрес https://example.com
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
