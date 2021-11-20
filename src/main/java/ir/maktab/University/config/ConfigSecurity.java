package ir.maktab.University.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceSecurity userDetailsServiceSecurity;

    public ConfigSecurity(UserDetailsServiceSecurity userDetailsServiceSecurity){
        this.userDetailsServiceSecurity = userDetailsServiceSecurity;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceSecurity);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();
/*                .antMatchers("/user/**")
                .permitAll()
                .antMatchers("/manager/**")
                .hasAuthority("ROLE_MANAGER")
                .antMatchers("/teacher/**")
                .hasAuthority("ROLE_TEACHER")
                .antMatchers("/student/**")
                .hasAuthority("ROLE_STUDENT")
                .anyRequest()
                .authenticated();*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
