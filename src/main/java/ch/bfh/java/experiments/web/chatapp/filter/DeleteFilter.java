package ch.bfh.java.experiments.web.chatapp.filter;

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

@WebFilter("/api3/messages/{id}")
public class DeleteFilter extends HttpFilter {
	private static final Logger logger = Logger.getLogger(DeleteFilter.class.getName());

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			String[] credentials = getCredentials(request);
			validateCredentials(credentials);
		} catch (Exception ex) { response.setStatus(401); return; }
		chain.doFilter(request, response);
	}

	private String[] getCredentials(HttpServletRequest request) throws Exception {
		String authHeader = request.getHeader("Authorization");
		String[] tokens = authHeader.split(" ");
		if (!tokens[0].equals("Basic")) throw new Exception();
		byte[] decoded = Base64.getDecoder().decode(tokens[1]);
		return new String(decoded, StandardCharsets.UTF_8).split(":");
	}

	private void validateCredentials(String[] credentials) throws Exception {
		if (!(credentials[0].equals("admin") && credentials[1].equals("12345"))) {
			logger.warning("Invalid credentials");
			throw new Exception("Invalid credentials");
		}
	}
}
