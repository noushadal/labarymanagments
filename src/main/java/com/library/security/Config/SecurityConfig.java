package com.library.security.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.library.security.cfg.jwt.CustomUserDetailsService;
import com.library.security.cfg.jwt.JwtAuthenticationEntryPoint;
import com.library.security.cfg.jwt.JwtAuthenticationFilter;

//import com.library.security.config.jwt.CustomUserDetailsService;
//import com.library.security.config.jwt.JwtAuthenticationEntryPoint;
//import com.library.security.config.jwt.JwtAuthenticationFilter;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig  {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
         
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
     
        return authProvider;
    }
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
      return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeHttpRequests()

                    .requestMatchers("/api/auth/**")
                        .permitAll()
                        .requestMatchers("/lab/assignBookToUser/{id}/{bookId}").permitAll()
                   // .requestMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                      //  .requestMatchers("/lab/adduser").hasRole("ADMIN")
                       // .requestMatchers("/lab/addBooks").hasAnyRole("ADMIN")
                      //  .requestMatchers("/lab/add/{userId}/{bookId}").hasAnyRole("ADMIN")
                       // .requestMatchers("/lab/delete/{userId}/{bookId}").hasAnyRole("ADMIN")
                        
                    //.requestMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
                        //.permitAll()
                    .anyRequest()
                        .authenticated();

        // Add our custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
