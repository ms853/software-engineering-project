package ecourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationFailureHandler authFailHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // security configuration
    http
        // page request permissions
        .authorizeRequests().antMatchers("/").permitAll().antMatchers("/courses/**").permitAll()
        .antMatchers("/about").permitAll().antMatchers("/error-login").permitAll()
        .antMatchers("/reset-password/**").permitAll().antMatchers("/resources/**").permitAll()
        .antMatchers("/signup/**").permitAll().antMatchers("/auth-continue/**").hasRole("PRE_AUTH")
        .antMatchers("/learner/**").hasRole("LEARNER").antMatchers("/teacher/**").hasRole("TEACHER")
        .antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()
        // login configuration
        .and().formLogin().loginPage("/").defaultSuccessUrl("/auth-continue", true)
        .loginProcessingUrl("/login").failureHandler(authFailHandler).permitAll()
        // logout configuration
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/")
        // exception handling configuration
        .and().exceptionHandling().accessDeniedPage("/access-denied")
        // secure connection
        .and().requiresChannel().anyRequest().requiresSecure();
  }

  @Autowired
  private AuthenticationProvider authProvider;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider);
  }

}
