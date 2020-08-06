 package com.demo.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
			//.passwordEncoder(new BCryptPasswordEncoder());
			//.rolePrefix("ROLE_");
			//;
		
		auth.jdbcAuthentication()
	     .dataSource(dataSource)
	     .usersByUsernameQuery("SELECT email as principal, password as credentials, etat FROM entreprise WHERE email =  ?")
	     .authoritiesByUsernameQuery("SELECT email as principal, nom as role FROM entreprise_roles WHERE email = ?")
	     .passwordEncoder(new BCryptPasswordEncoder());
	  //   .rolePrefix("ROLE_");
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin();//pour afficher un formulaire de connexion par defaut
		http.formLogin().loginPage("/login");//personnaliser le form de login
		// /online/roles
		//les droits dun USER
		http.authorizeRequests().antMatchers("/Entreprise/**").hasAnyAuthority("ROLE_USER");
		//les droits du role ETUDIANT
		http.authorizeRequests().antMatchers("/Demandeur/**").hasAnyAuthority("ROLE_USERT", "ROLE_PROFESSEUR");
		//les droits du role PROFESSEUR
		http.authorizeRequests().antMatchers("/Offre/**").hasAuthority("ROLE_USER");
		
	//	http.authorizeRequests().antMatchers("/online/etudiantcours").hasAuthority("ROLE_PROFESSEUR");
		//gestion des droits
	http.exceptionHandling().accessDeniedPage("/403");
	//	http.csrf().disable();
	}

}
