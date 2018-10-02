package edu.shah.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
				.antMatchers("/","/login","/home","/listProducts/**").permitAll()
				.antMatchers("/addProduct").hasAnyRole("EMP","ADMIN")
				.antMatchers("/deleteProduct").hasAnyRole("ADMIN")
				.antMatchers("/prduct").hasAnyRole("CUSTOMER")
				.anyRequest().authenticated()
				.and()
			.exceptionHandling().accessDeniedPage("/403")
				.and()
				.formLogin()
				.failureUrl("/login?error")
				.defaultSuccessUrl("/home")
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();		
	}
	
	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
					.withUser("user2@mail.com").password(passwordEncoder.encode("user2")).roles("ADMIN","EMP");
		auth.inMemoryAuthentication()
					.withUser("user3@mail.com").password(passwordEncoder.encode("user3")).roles("EMP","CUSTOMER");
		auth.inMemoryAuthentication()
					.withUser("user4@mail.com").password(passwordEncoder.encode("user4")).roles("CUSTOMER");
	}
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
