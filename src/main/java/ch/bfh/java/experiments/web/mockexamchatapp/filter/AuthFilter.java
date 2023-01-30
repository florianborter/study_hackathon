package ch.bfh.java.experiments.web.mockexamchatapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

@WebFilter("/api4/messages/*")
public class AuthFilter extends HttpFilter {

    private static final Logger logger = Logger.getLogger(AuthFilter.class.getName());

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request.getMethod().equalsIgnoreCase("delete")) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || authHeader.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                logger.info("Got request on authenticated endpoint, but no Authorization header supplied.");
                return;
            }
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            if (credentials.equals("admin:12345")) {
                chain.doFilter(request, response);
                return;
            }
            logger.info("Got wrong username / password for message delete");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }
}
