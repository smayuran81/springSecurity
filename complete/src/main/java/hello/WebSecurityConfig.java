package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.
		inMemoryAuthentication().		
		withUser("user").password("{noop}pass").
		roles("USER");
			
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/signup","/user/register").permitAll()
			.anyRequest().authenticated()			
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.loginProcessingUrl("/login")
		.and().logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
		.and().csrf().disable();
	}
	
   
}