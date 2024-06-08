package com.ues.fia.bad115;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
// @EnableWebSecurity
public class SecurityConfig {

    /*
     * @Bean
     * public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
     * Exception {
     * http
     * .authorizeHttpRequests(authorize -> authorize
     * .requestMatchers("/admin/**").hasRole("ADMIN")
     * .requestMatchers("/librarian/**").hasAnyRole("ADMIN", "LIBRARIAN")
     * .requestMatchers("/member/**").hasAnyRole("ADMIN", "LIBRARIAN", "MEMBER")
     * .anyRequest().authenticated())
     * .formLogin(form -> form
     * .permitAll())
     * .logout(logout -> logout
     * .permitAll());
     * return http.build();
     * }
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * @Bean
     * public UserDetailsService userDetailsService() {
     * return new CustomUserDetailsService();
     * }
     * 
     * @Autowired
     * public void configureGlobal(AuthenticationManagerBuilder auth) throws
     * Exception {
     * auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder
     * ());
     * }
     */
}
