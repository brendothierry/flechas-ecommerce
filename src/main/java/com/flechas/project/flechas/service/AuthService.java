package com.flechas.project.flechas.service;

import com.flechas.project.flechas.dto.AuthRequest;
import com.flechas.project.flechas.dto.AuthResponse;
import com.flechas.project.flechas.model.Usuario;
import com.flechas.project.flechas.repository.UserRepository;
import com.flechas.project.flechas.config.JwtConfig;
import com.flechas.project.flechas.util.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtConfig jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Usuario registrar(Usuario usuario) {
        if (userRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        if (usuario.getRole() == null) {
            usuario.setRole(Role.USER);
        }
        return userRepository.save(usuario);
    }

    public AuthResponse login(AuthRequest request) {
        // Autentica usuário (verifica senha via AuthenticationManager)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );
        // Busca usuário
        var usuario = userRepository.findByEmail(request.getEmail()).
                orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Gera token JWT
        var jwtToken = jwtService.generateToken(usuario);

        return new AuthResponse(jwtToken, usuario.getEmail(), usuario.getRole().name());
    }

}
