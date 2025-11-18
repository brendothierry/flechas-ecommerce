package com.flechas.project.flechas.controller;

import com.flechas.project.flechas.dto.AuthRequest;
import com.flechas.project.flechas.dto.AuthResponse;
import com.flechas.project.flechas.model.Usuario;
import com.flechas.project.flechas.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.registrar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @GetMapping
    public ResponseEntity<Usuario> getAllUsers() {

        return ResponseEntity.ok().build();
    }
}
