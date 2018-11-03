package edu.shah.web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private DataSource dataSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().antMatchers("/", "/login", "/home", "/listProducts/**").permitAll()
				.antMatchers("/static/**").permitAll().antMatchers("/images/**").permitAll().antMatchers("/fonts/**")
				.permitAll().antMatchers("/deleteCustomer").hasAnyRole("ADMIN").antMatchers("/img/**/**").permitAll()
				.antMatchers("/addedProduct").hasAnyRole("ADMIN", "EMP").antMatchers("/js/**").permitAll()
				.antMatchers("/css/**").permitAll().antMatchers("/addProduct").hasAnyRole("EMP", "ADMIN")
				.antMatchers("/orderHistory").hasAnyRole("CUSTOMER").antMatchers("/checkout")
				.hasAnyRole("CUSTOMER", "ADMIN", "EMP").antMatchers("/viewCart").hasRole("CUSTOMER")
				.antMatchers("/deleteProduct").hasAnyRole("ADMIN").antMatchers("/browsePurchaseOrders")
				.hasAnyRole("ADMIN", "EMP").antMatchers("/product").hasAnyRole("CUSTOMER").antMatchers("/register/**")
				.permitAll().antMatchers("/403").permitAll().anyRequest().authenticated().and().formLogin()
				.failureUrl("/login?error").defaultSuccessUrl("/registerHome").loginPage("/login").permitAll().and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user2@mail.com").password(passwordEncoder.encode("user2"))
				.roles("ADMIN", "EMP");
		auth.inMemoryAuthentication().withUser("user3@mail.com").password(passwordEncoder.encode("user3")).roles("EMP",
				"CUSTOMER");
		auth.inMemoryAuthentication().withUser("user4@mail.com").password(passwordEncoder.encode("user4"))
				.roles("CUSTOMER");
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("select email,password,active from inventorydb.account where email=?")
				.authoritiesByUsernameQuery("select email,role from inventorydb.authority where email=?");
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}