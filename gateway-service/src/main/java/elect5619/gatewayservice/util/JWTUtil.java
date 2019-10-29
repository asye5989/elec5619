package elect5619.gatewayservice.util;

import java.util.Date;
import java.util.HashMap;

import elect5619.gatewayservice.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	private final static long TOKEN_EXPIRE_TIME_IN_MILLISECONDS = 14400000L;// 4 hours default expire time
	private final static String ISSUER = "ELECT5619_GATEWAY_SERVICE";
	private final static String AUDIENCE = "ADMIN_OR_CLIENTUI_OF_ELEC5619_APP";

	public static String buildToken(String email, String signKey) {
		long currentTimeInMilliseconds = System.currentTimeMillis();
		long expireTimeMilliSec = currentTimeInMilliseconds + TOKEN_EXPIRE_TIME_IN_MILLISECONDS;

		Date issueTime = new Date(currentTimeInMilliseconds);
		Date expireTime = new Date(expireTimeMilliSec);

		return Jwts.builder().setClaims(new HashMap<>()).setIssuer(ISSUER).setSubject(email).setAudience(AUDIENCE)
				.setIssuedAt(issueTime).setExpiration(expireTime).signWith(SignatureAlgorithm.HS512, signKey)
				.compact();
		 

	}

	public static void validateToken(String token, String signKey) throws InvalidTokenException {
		try {
			Claims claims = getAllClaimsFromToken(token , signKey);
			if (!(claims.getExpiration().after(new Date()) && claims.getIssuer().equals(ISSUER)))
				throw new InvalidTokenException();
			// if the token decrypts with password, issuer is gateway-service and also not expired it is valid
		} catch (InvalidTokenException e) {
			throw e;
		} catch (Exception e) {
			throw new InvalidTokenException();
		}
	}

	private static Claims getAllClaimsFromToken(String token, String signkey) {
		return Jwts.parser().setSigningKey(signkey).parseClaimsJws(token).getBody();
	}

}
