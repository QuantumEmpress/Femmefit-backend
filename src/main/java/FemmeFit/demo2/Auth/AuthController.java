package FemmeFit.demo2.Auth;

import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user and get the User object
            User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());

            // Generate a token (e.g., JWT)
            String token = generateToken(user);

            // Return the token in the response
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (RuntimeException e) {
            // Handle authentication errors
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    private String generateToken(User user) {
        // Set token expiration time (e.g., 1 hour)
        long expirationTime = 1000 * 60 * 60; // 1 hour in milliseconds
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        // Generate the token
        return Jwts.builder()
                .setSubject(user.getEmail()) // Use email as the subject
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, "your-secret-key") // Use a secure secret key
                .compact();
    }
}