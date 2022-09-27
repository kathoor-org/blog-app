package com.bladerunner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bladerunner.security.CustomUserDetailsService;

/*
 * @Configuration //@EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired private CustomUserDetailsService customUserDetailsService;
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * 
 * http.csrf().disable().authorizeHttpRequests().anyRequest().authenticated().
 * and().httpBasic(); }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception {
 * 
 * //auth.userDetailsService(this.customUserDetailsService).passwordEncoder(
 * passwordEncoder()); }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * }
 */
