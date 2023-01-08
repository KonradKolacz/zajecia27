package com.example.zajecia27.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final UserDetailsService userDetailsService;
    private final String secret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, String secret) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.secret = secret;
    }

//    1)Pobieram obiekt autoryzacji, jeśli nie istnieje, przekazuję sterowanie do kolejnego filtra.
//    2)Jeśli istnieje, ustawiam obiekt w SecurityContextHolder.
//    3)Pobieram tokena z nagłówka Authorization sprawdzam, czy zawiera prefix Bearer.
//    4)Inicjalizuję weryfikację tokena.
//    5)Używam metody verify(...) do sprawdzenia poprawności sygnatury tokena (przy okazji wycina z tokena prefix).
//    6)Pobieram subject (w tym wypadku jest to nazwa usera).
//    7)Pobieram użytkownika z userDetailsService (którego wcześniej wstrzykuję w konstruktorze).
//    8)Tworzę UsernamePasswordAuthenticationToken w oparciu o pobrane UserDetails.

    //1 - client daje zapytanie do serwera. tutaj sprawdzam czy ma juz tokena , jesli tak to zwracam response.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException, IOException {
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request); //1
        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);  //2
        filterChain.doFilter(request, response);
    }

    //3 - validation username and password  loadUserByUsername / if user valid return valid token(point 4)
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);  //3
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            String userName = JWT.require(Algorithm.HMAC256(secret))  //4
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, "")) //5
                    .getSubject();  //6
            if (userName != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName); //7
                return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()); //8
            }
        }
        return null;
    }

}
