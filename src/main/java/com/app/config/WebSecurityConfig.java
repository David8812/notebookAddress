package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends AbstractSecurityWebApplicationInitializer {
	
	@Configuration
	@Order(2)
	public class WebSecurityConfigurationWithoutLogin extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private CustomAutenticationProvider customAutenticationProvider;

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(customAutenticationProvider);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/lang/**").authorizeRequests().anyRequest().permitAll();
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}

	@Configuration
	@Order(3)
	public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired
		private CustomAutenticationProvider customAutenticationProvider;

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(customAutenticationProvider);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
			.antMatchers("/validateLoggin", "/logout", "/registerNewUser", "/saveNewUser").permitAll()
			//.regexMatchers(".*/\\?language=.+").permitAll()
			.regexMatchers(".*/callFromSearch\\?search=\\&agregarNuevo/?.*", "/callFromSearchPart\\?search=\\&agregarNuevo/?.*", "/editPartner?.*", ".*/editFam?.*").hasRole("ADMIN")
			.anyRequest().authenticated().and().formLogin().loginPage("/").permitAll().and()
			.logout().permitAll().and().exceptionHandling().accessDeniedPage("/forbidden.jsp");
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}

	@Configuration
	@Order(1)
	public static class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

		private static String REALM = "MY_TEST_REALM";

		@Autowired
		private CustomAutenticationProvider customAutenticationProvider;

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(customAutenticationProvider);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api_rest/**").authorizeRequests().antMatchers(HttpMethod.POST).hasRole("ADMIN")
					.antMatchers(HttpMethod.DELETE).hasRole("ADMIN").anyRequest().authenticated().and().httpBasic()
					.realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint()).and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
		}

		@Bean
		public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
			return new CustomBasicAuthenticationEntryPoint();
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		// To allow Pre-flight [OPTIONS] request from browser

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		}
	}

}
