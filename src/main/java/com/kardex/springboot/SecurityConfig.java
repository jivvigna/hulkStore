/***
 * @author jvigna
 * 
 * Clase encargada de la configuracion del aplicativo - Se ejecuta ni bien arranca la aplicacion
 */
package com.kardex.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kardex.springboot.service.EmployeeServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private static Logger LOG =  LoggerFactory.getLogger(SecurityConfig.class);

	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		LOG.debug("Inicio - Inyentando dependencia de encriptacion de claves");
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		LOG.debug("Fin - Inyentando dependencia de encriptacion de claves");
		
		return bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		
		LOG.debug("Inicio - Configurando la clase encargada de la autenticacion EmployeeServiceImpl con el encriptador");
		
		authenticationManagerBuilder.userDetailsService(employeeService).passwordEncoder(bCryptPasswordEncoder);
		
		LOG.debug("Fin - Configurando la clase encargada de la autenticacion EmployeeServiceImpl con el encriptador");

	}
	
	@Override
	protected void configure (HttpSecurity httpSecurity) throws Exception{
		
		LOG.debug("Inicio - Configurando el acceso a los recursos");

		httpSecurity.csrf().disable()
        .authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout().
        permitAll();
		
		LOG.debug("Fin - Configurando el acceso a los recursos");
		
	}

}
