//package FemmeFit.demo2.controller;
//
//import FemmeFit.demo2.entity.User;
//import FemmeFit.demo2.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping
//    public ResponseEntity<String> userLogin(@RequestBody User user) {
//        boolean isAuthenticated = authService.authenticateUser(user.getEmail(), user.getPassword());
//
//        if (isAuthenticated) {
//            // Return session ID or simple success message
//            return ResponseEntity.ok("login successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//    }
//}
//
//package FemmeFit.demo2.controller;
//
//import FemmeFit.demo2.entity.User;
//import FemmeFit.demo2.service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/login") // Changed to explicit /login endpoint
//    public ResponseEntity<String> userLogin(@RequestBody User user) {
//        boolean isAuthenticated = authService.authenticateUser(user.getEmail(), user.getPassword());
//
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }
//}
package FemmeFit.demo2.controller;

import FemmeFit.demo2.entity.User;
import FemmeFit.demo2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody User user) {
        boolean isAuthenticated = authService.authenticateUser(user.getEmail(), user.getPassword());

        Map<String, Object> response = new HashMap<>();

        if (isAuthenticated) {
            // Get the user details (you'll need to implement this in your AuthService)
            User authenticatedUser = authService.getUserByEmail(user.getEmail());

            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("token", "your-generated-jwt-token"); // You'll need to implement JWT
            response.put("user", authenticatedUser);

            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }
    }
}