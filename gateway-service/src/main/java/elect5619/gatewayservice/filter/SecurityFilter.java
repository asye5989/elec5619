package elect5619.gatewayservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import elect5619.gatewayservice.domain.User;
import elect5619.gatewayservice.exception.InvalidTokenException;
import elect5619.gatewayservice.exception.SessionExpiredException;
import elect5619.gatewayservice.service.SecurityService;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	SecurityService securityService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwtToken = request.getHeader("Authorization");
//		User userDetails = null;
//		try {
//			if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
//				jwtToken = jwtToken.substring(7).trim();
//			}
//			userDetails = securityService.validateRawToken(jwtToken);
//			if (!userDetails.isAdmin() && request.getContextPath().toLowerCase().contains("admin")) {
//				// check if non admin user accessing admin site 
//				logger.warn("Valid Token : un authorized access to Admin  ::" + userDetails.getUsername());
//				userDetails = null;// nullify all authentication process  
//			}
//			response.setHeader("X-jwt", "Verified");
//		} catch (InvalidTokenException e) {
//			logger.warn("Invalid Token Exception ; token origin " + request.getRemoteHost());
//		} catch (SessionExpiredException e) {
//			logger.warn("Expired Token Exception ; token origin " + request.getRemoteHost());
//		} catch (NullPointerException e) {
//			logger.warn("No Token present ; request origin " + request.getRemoteHost());
//		}
//
//		if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			// After setting the Authentication in the context, we specify that the current
//			// user is authenticated. So it passes the Spring Security Configurations
//			// successfully.
//			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//					userDetails, null, userDetails.getAuthorities());
//			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//		}

		filterChain.doFilter(request, response);

	}

}
