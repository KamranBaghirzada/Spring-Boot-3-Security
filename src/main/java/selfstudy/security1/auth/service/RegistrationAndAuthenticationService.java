package selfstudy.security1.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import selfstudy.security1.auth.dao.AuthenticationAndRegistrationResponse;
import selfstudy.security1.auth.dao.AuthenticationRequest;
import selfstudy.security1.auth.dao.RegistrationRequest;
import selfstudy.security1.config.JwtService;
import selfstudy.security1.user.Role;
import selfstudy.security1.user.User;
import selfstudy.security1.user.UserRepository;

@Service
@RequiredArgsConstructor
public class RegistrationAndAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationAndRegistrationResponse registerUser(RegistrationRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationAndRegistrationResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthenticationAndRegistrationResponse authenticateUser(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        String jwt = jwtService.generateToken(user);
        return AuthenticationAndRegistrationResponse.builder()
                .token(jwt)
                .build();
    }
}
