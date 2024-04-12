package Config;

import Enums.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/admin/**").hasRole(String.valueOf(Role.ADMIN))
                .antMatchers(HttpMethod.GET, "/api/user/viewAvailableProducts").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/bookOrders").hasRole(String.valueOf(Role.USER))
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
