package com.pgs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.pgs.service.SecurityUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthFailuer authFailure;
	
	@Autowired
	AuthSuccess authSuccess;
	
	@Autowired
	SecurityUserService securityUserService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication().withUser("test").password("test").authorities("ADMIN");
		auth.userDetailsService(securityUserService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	

	
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable()
		.formLogin()
			.loginPage("/resources/login.html").permitAll()
			.loginProcessingUrl("/api/login")
			//.usernameParameter("username")
			//.passwordParameter("password")
			.failureHandler(authFailure)
			.successHandler(authSuccess)
		.and()
		.logout().logoutSuccessUrl("/")
		.and()
		.authorizeRequests().antMatchers("/resources/js/**").permitAll()
		//.and().authorizeRequests().antMatchers("/resources/api/**").permitAll()
		//.and().authorizeRequests().antMatchers("/**").permitAll()
		.and().authorizeRequests().anyRequest().authenticated();
	}
	/*	http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/resources/failure.html").permitAll()
		.antMatchers("/resources/js/**").permitAll()
		.anyRequest().authenticated().and()
        .formLogin().defaultSuccessUrl("/resources/home.html")
        .loginPage("/resources/login.html")
        .failureUrl("/resources/failure.html").loginProcessingUrl("/EventTracker/auth").usernameParameter("username")
        .passwordParameter("password").permitAll()//.successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
        .successHandler(authSuccess)
        .failureHandler(authFailure)                  
        .and().logout().permitAll()
        .and()
    .httpBasic();
		
	}*/

}
