package com.davifrjose.eventus.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

   @Autowired
  SecurityFilter securityFilter;

  private static final String[] SWAGGER_KIST = {
    "/swagger-ui/**",
    "/v3/api-docs/**",
    "swagger-resources/**"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
  {
    return httpSecurity
      .csrf(csrf ->  csrf.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(authorize -> authorize
          .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
          .requestMatchers(HttpMethod.POST, "/user/order/").permitAll()
          .requestMatchers(HttpMethod.GET, "/event/").permitAll()
          .requestMatchers(HttpMethod.GET, "/ticket/").permitAll()
          .requestMatchers(SWAGGER_KIST).permitAll()
          .requestMatchers(HttpMethod.POST, "/organizer/").hasRole("ADMIN")
          .requestMatchers(HttpMethod.POST, "/event/").hasRole("ADMIN")
          .requestMatchers(HttpMethod.POST, "/ticket/").hasRole("ADMIN")          
          .anyRequest().authenticated()
      )
      .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
      .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
  {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }
}
