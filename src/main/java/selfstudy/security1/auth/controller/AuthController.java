package selfstudy.security1.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import selfstudy.security1.auth.dao.AuthenticationRequest;
import selfstudy.security1.auth.dao.AuthenticationAndRegistrationResponse;
import selfstudy.security1.auth.dao.RegistrationRequest;
import selfstudy.security1.auth.service.RegistrationAndAuthenticationService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationAndAuthenticationService registrationAndAuthenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationAndRegistrationResponse> register(@RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(registrationAndAuthenticationService.registerUser(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationAndRegistrationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(registrationAndAuthenticationService.authenticateUser(request));
    }
}
