package br.com.iteam.infrastructure.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Component
public class CustomBasicAuthFilter extends OncePerRequestFilter {

    private static final String BASIC_PREFIX = "Basic ";
    private static final int BASIC_PREFIX_LENGTH = BASIC_PREFIX.length();

    private static final Map<String, String> USER_CREDENTIALS = Map.of(
            "admin", "admin123",
            "user", "user123"
    );

    private static final Map<String, List<SimpleGrantedAuthority>> USER_ROLES = Map.of(
            "admin", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")),
            "user", List.of(new SimpleGrantedAuthority("ROLE_USER"))
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization == null || !headerAuthorization.startsWith(BASIC_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String encodedCredentials = headerAuthorization.substring(BASIC_PREFIX_LENGTH);
            byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
            String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);

            String[] credentials = decodedCredentials.split(":", 2);
            if (credentials.length != 2) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials");
                return;
            }

            String username = credentials[0];
            String password = credentials[1];

            if (USER_CREDENTIALS.containsKey(username) && USER_CREDENTIALS.get(username).equals(password)) {
                var authorities = USER_ROLES.getOrDefault(username, List.of());
                var authToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
                return;
            }

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication error");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
