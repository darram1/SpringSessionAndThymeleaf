package es.iesjandula.springsessionandthymeleaf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConnfig
{
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http)
    {
        try
        {
            http.authorizeHttpRequests()
                    .requestMatchers("/userVisits", "/sumar/**").permitAll()
                    .requestMatchers("/", "/submit", "/otro_ejemplo").permitAll()
                    .requestMatchers("/index.html", "/login.html","/login-error.html","/forbidden.html","/css/**").permitAll()

                    .requestMatchers("/admin/index.html").hasRole("ADMIN")
                    .requestMatchers("/user/index.html").hasAnyRole("ADMIN", "USER")

                    .anyRequest().authenticated()

                    .and()

                    .formLogin()
                        .loginPage("/login.html")
                    .failureUrl("/login-error.html")

                    .and()
                        .logout()
                        .logoutSuccessUrl("/index.html")
                    .and()
                        .exceptionHandling()
                            .accessDeniedPage("/forbidden.html");
            return http.build();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService()
    {
        return new InMemoryUserDetailsManager(
                User.withUsername("paco").password("{noop}demo").roles("ADMIN").build(),
                User.withUsername("pepe").password("{noop}demo").roles("USER").build(),
                User.withUsername("juan").password("{noop}demo").roles("USER","ADMIN").build()
        );
    }
}
