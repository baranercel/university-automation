package com.springboot.demo2.controllers;

import com.springboot.demo2.dtos.AuthRequestDTO;
import com.springboot.demo2.dtos.AuthResponseDTO;
import com.springboot.demo2.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO AuthRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(AuthRequest.getEmail(), AuthRequest.getPassword())
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();

        String role = user.getAuthorities().iterator().next().getAuthority();

        String token = jwtService.generateJwtToken(user.getUsername(),  role);

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
