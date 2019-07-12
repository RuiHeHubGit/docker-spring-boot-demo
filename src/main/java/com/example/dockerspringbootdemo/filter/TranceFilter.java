package com.example.dockerspringbootdemo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@WebFilter(urlPatterns = "/*", filterName = "trance")
public class TranceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Map<String, String> trailerFields = request.getTrailerFields();
        if(!trailerFields.containsKey("tranceId")) {
            trailerFields.put("tranceId", UUID.randomUUID().toString());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
