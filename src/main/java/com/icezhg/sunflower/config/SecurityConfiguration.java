package com.icezhg.sunflower.config;

import com.icezhg.sunflower.security.configurer.AuthenticatedRequestConfigurer;
import com.icezhg.sunflower.security.configurer.CaptchaConfigurer;
import com.icezhg.sunflower.security.configurer.UsernamePasswordLoginConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.cors.CorsUtils;

/**
 * Created by zhongjibing on 2023/06/17.
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/actuator/*").permitAll()
                        .requestMatchers("/authenticated").permitAll()
                        .requestMatchers(HttpMethod.GET, "/picture/*").permitAll()
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        .anyRequest().authenticated())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .defaultLogoutSuccessHandlerFor(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT), AnyRequestMatcher.INSTANCE)
                        .deleteCookies("SESSION")
                        .invalidateHttpSession(true))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new Http403ForbiddenEntryPoint()))
                .headers(headers -> headers.cacheControl(HeadersConfigurer.CacheControlConfig::disable))
                .sessionManagement(session -> session.maximumSessions(1));

        http.apply(new CaptchaConfigurer<>());
        http.apply(new UsernamePasswordLoginConfigurer<>());
        http.apply(new AuthenticatedRequestConfigurer<>());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
