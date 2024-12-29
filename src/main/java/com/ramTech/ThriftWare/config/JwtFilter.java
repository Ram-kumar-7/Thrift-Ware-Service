package com.ramTech.ThriftWare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ramTech.ThriftWare.models.User;
import com.ramTech.ThriftWare.service.AuthService;
import com.ramTech.ThriftWare.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws java.io.IOException, jakarta.servlet.ServletException {
        String auth = request.getHeader("Authorization");
        String token = null;
        String mailId = null;

        if (auth != null) {
            if (auth.startsWith("Bearer ")) {
                token = auth.substring(7);
                mailId = jwtService.extractMailId(token);
            } else {
                token = auth;
            }
            mailId = jwtService.extractMailId(token);
        }

        if (mailId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = applicationContext.getBean(AuthService.class).getUserDetails(mailId);
            if (jwtService.validateToken(token, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null,
                        null);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
