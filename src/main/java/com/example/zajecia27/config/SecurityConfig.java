package com.example.zajecia27.config;

import com.example.zajecia27.filter.JsonObjectAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper;
    private final RestAuthenticationFailureHandler failureHandler; //for Jwt
    private final RestAuthenticationSuccessHandler successHandler; //for Jwt
     private final String secret;                                //for Jwt

//    for JWT
    public SecurityConfig(UserDetailsService userDetailsService, ObjectMapper objectMapper, RestAuthenticationFailureHandler failureHandler, RestAuthenticationSuccessHandler successHandler, @Value("${jwt.secret}") String secret) {
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
        this.secret = secret;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/app").permitAll()
                .antMatchers(HttpMethod.GET,"/admin/welcome").authenticated()
                .antMatchers(HttpMethod.GET,"/user/welcome").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll() //dodane do sprawdzenia dzialania security poprzez http i cwiczen thymeleaf (zakomentowac jesli dzialam z jwt)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //zeby moja sesja byla bezstanowa
//                .and()
//                .addFilter(authenticationFilter())//dodane do cwiczen z jwt
//                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService(), secret))//dodane do cwiczen z jwt.
//                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .logout().permitAll().invalidateHttpSession(true).deleteCookies("JSESSIONID").and()//wygasniecie sesji po wylogowaniu
                .csrf().disable();

        return http.build();
    }

    //for jwt
//    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
//        JsonObjectAuthenticationFilter authenticationFilter = new JsonObjectAuthenticationFilter(objectMapper);
//        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
//        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
//        authenticationFilter.setAuthenticationManager(super.authenticationManager());
//        return authenticationFilter;
//    }

// KIEDYŚ
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }



    //    ponizej weryfikujemy z danymi poprzez jdbc a jesli poprzez Spring Data JPA to uzywamy UserDetailService
//    mozna tez uzyc wlasnego providera uwierzytelniania czyli rozszerzyc DaoAuthenticationProvider

    //    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select email, password...")
//                .authoritiesByUsernameQuery("select u.email, r.role_name from users_roles...")
//                .passwordEncoder(passwordEncoder()).and().build();
//    }


//    1
//    protected void configure(final AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authProvider());
//    }

//    public DaoAuthenticationProvider authProvider() {
//    final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService);
//    authProvider.setPasswordEncoder(passwordEncoder());
//    return authProvider;
//}

//    2
//@Bean
//public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//    final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
//    authProvider.setUserDetailsService(userDetailsService);
//    authProvider.setPasswordEncoder(passwordEncoder());
//    return http.getSharedObject(AuthenticationManagerBuilder.class)
//            .authenticationProvider(authProvider).build();
//}



}
