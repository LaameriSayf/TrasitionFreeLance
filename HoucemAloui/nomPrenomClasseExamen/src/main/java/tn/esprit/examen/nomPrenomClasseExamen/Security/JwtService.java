package tn.esprit.examen.nomPrenomClasseExamen.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "Hi7isV+331vIL/OyyMAgDov2wKus6avDz2nhO/D9gsr1/gJcPJAbxR7mZd0djHl6cgIvdQJ49ByBqAAJ2ihn6wjpE7BhqoS7TPwlLmE5DsakFAFJDHimxddWz2fo45QBw48h5Z4TW7QhRyHK2bsQiF5AV3tXpTO3SQtwaXVTKo7CZ1YYw3Fbzf4k9mKoCamLUaYM3KvXZRCR/8w+nYYPKXYZtYycHf9KFDEWVchSrjHk7dAXoyOCnMkD8tn5lbN/yOIsEe1LMAOqbQIo4GOSux38l+RV4mZGmzWMCQDSnnwq4hcyFNd8DR0E/hugzdDmo90HRw8FAOIucy9tG//aaoYaoZvzvA0J0mU+GXp+ZUM=";
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);//subject containes username or email of user this how we can ectract
    }
    public String extractPrenom(String token) {
        return extractClaim(token, claims -> claims.get("prenom", String.class));
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final  Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()))&&!isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(Map<String,Object>extractClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setAllowedClockSkewSeconds(60 * 60 * 24) // Tolérance de 24h
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); //one of allgoritheme hmacshakeyfor

    }
}
