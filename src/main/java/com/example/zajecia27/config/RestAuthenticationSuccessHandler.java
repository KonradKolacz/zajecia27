package com.example.zajecia27.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
// sluzy do zwracania tokena przez endpoint /login

    private final long expirationTime;
    private final String secret;

    public RestAuthenticationSuccessHandler(
            @Value("${jwt.expirationTime}") long expirationTime,
            @Value("${jwt.secret}") String secret) {
        this.expirationTime = expirationTime;
        this.secret = secret;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetails principal = (UserDetails) authentication.getPrincipal(); //1
        String token = JWT.create() //2
                .withSubject(principal.getUsername()) //3
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime)) //4
                .sign(Algorithm.HMAC256(secret)); //5

        response.addHeader("Authorization", "Bearer " + token);
//        response.getOutputStream().print("{\"token\": \"" + token + "\"}"); // 6
    }


//1) Pobieram szczegóły zalogowanego użytkownika.
//2)Tworzę builder.
//3)Dodaję nazwę użytkownika jako subject.
//4)Ustawiam datę wygaśnięcia. Zmienna expirationTime jest ustawiana w konfiguracji aplikacji.
//5)Ustawiam algorytm na HMAC256 dla secret (ta zmienna też jest pobierana z konfiguracji). Podpisuję i tworzę tokena (metoda .sing() robi te dwie rzeczy)
//6)Wypisuje jsona zawierającego tokena na OutputStream. Robię to w bardzo prosty sposób (konkatencja), ponieważ tworzenie obiektu opakowującego i zamienianie go na jsona poprzez ObjectMapper wydało mi się trochę nadmiarowe.
}
