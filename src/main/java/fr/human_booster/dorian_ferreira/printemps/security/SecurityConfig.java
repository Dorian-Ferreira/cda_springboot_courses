package fr.human_booster.dorian_ferreira.printemps.security;

import fr.human_booster.dorian_ferreira.printemps.route.UrlRoute;
import fr.human_booster.dorian_ferreira.printemps.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private JwtTokenFilter jwtTokenFilter;
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        AntPathRequestMatcher.antMatcher(UrlRoute.LOGIN),
                        AntPathRequestMatcher.antMatcher(UrlRoute.REGISTER),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, UrlRoute.BASE_LODGING + "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, UrlRoute.BASE_REVIEW + "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, UrlRoute.BASE_USER + "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, UrlRoute.BASE_ROOM_TYPE + "/**")
                    ).permitAll()

                    .requestMatchers(
                        AntPathRequestMatcher.antMatcher(UrlRoute.BASE_USER + "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.POST, UrlRoute.BASE_REVIEW + "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.POST, UrlRoute.BASE_BOOKING + "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, UrlRoute.BOOKING_CANCEL + "/**"),
                        AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, UrlRoute.REVIEW_DELETE + "/**")
                    ).authenticated()

                    .requestMatchers(
                            AntPathRequestMatcher.antMatcher("/api/**")
                    ).hasRole("ADMIN")
            );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
