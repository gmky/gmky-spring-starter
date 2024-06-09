package gmky.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {
    private final String base64Secret;

    private final long defaultExpireInSeconds;

    private final String applicationName;

    private final UserDetailsService userDetailsService;

    @Override
    public String generateToken(String username, Map<String, Object> context) {
        return Jwts.builder()
                .claims(context)
                .issuer(applicationName)
                .subject(username)
                .signWith(getSigningKey())
                .expiration(calExpiration(defaultExpireInSeconds))
                .compact();
    }

    @Override
    public String generateToken(String username, Map<String, Object> context, long expiration) {
        return Jwts.builder()
                .claims(context)
                .issuer(applicationName)
                .subject(username)
                .signWith(getSigningKey())
                .expiration(calExpiration(expiration))
                .compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        var username = extractClaim(token, Claims::getSubject);
        var user = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities());
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date calExpiration(long expiration) {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
