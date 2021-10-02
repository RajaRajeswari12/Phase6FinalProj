package com.IDICBank.bankingService.config;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService customUserDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(customUserDetailsService)
        	.passwordEncoder(passwordEncoder());
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http        	
        	.headers()
        		.frameOptions().sameOrigin()
        		.and()
            .authorizeRequests()
            	.antMatchers("/resources/**", "/webjars/**","/assets/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .permitAll()
                .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/login?logout")
            	.deleteCookies("my-remember-me-cookie")
                .permitAll()
                .and()
                .rememberMe()
             	//.key("my-secure-key")
             	.rememberMeCookieName("my-remember-me-cookie")
             	.tokenRepository(persistentTokenRepository())
             	.tokenValiditySeconds(24 * 60 * 60)
             	.and()
            .exceptionHandling().accessDeniedPage("/403").and()
            .csrf()
             	;
    }
    
    PersistentTokenRepository persistentTokenRepository(){
    	JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
    	tokenRepositoryImpl.setDataSource(dataSource);
    	return tokenRepositoryImpl;
    }
}



//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	UserDetailsService customUserDetailsService;
//	
//
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//	http.authorizeRequests()
//	.antMatchers("/admin").hasAnyAuthority("ADMIN")
//	.antMatchers("/user").hasAnyAuthority("USER")
//	.antMatchers("/").permitAll()
//	.and().formLogin().and()
//	   .exceptionHandling().accessDeniedPage("/403");
//		
////		   http.authorizeRequests()
////		   .antMatchers("/hello").access("hasRole('ADMIN')")
////		   .anyRequest().permitAll()
////		   .and()
////		     .formLogin().loginPage("/login")
////		     .usernameParameter("username").passwordParameter("password")
////		   .and()
////		     .logout().logoutSuccessUrl("/login?logout") 
////		    .and()
////		    .exceptionHandling().accessDeniedPage("/403")
////		   .and()
////		     .csrf();
//	}
//	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
//	
//	
//
//}
