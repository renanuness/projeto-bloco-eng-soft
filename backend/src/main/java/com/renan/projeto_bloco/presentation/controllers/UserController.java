package com.renan.projeto_bloco.presentation.controllers;

import com.renan.projeto_bloco.application.dto.UserLoginDTO;
import com.renan.projeto_bloco.application.dto.UserRequestDTO;
import com.renan.projeto_bloco.application.dto.UserResponseDTO;
import com.renan.projeto_bloco.domain.exceptions.DomainException;
import com.renan.projeto_bloco.domain.models.User;
import com.renan.projeto_bloco.domain.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll(){
        var users = userService.listAll();

        return ResponseEntity.ok(users.stream().map(u -> UserResponseDTO.fromDomain(u)).toList());
    }

    // cadastrar
    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRequestDTO dto) {
        try {
            User user = userService.cadastrar(dto);
            UserResponseDTO response = UserResponseDTO.fromDomain(user);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();

            return ResponseEntity.created(location).body(response);
        } catch (DomainException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@Valid @RequestBody UserLoginDTO dto){
        try {
            User user = userService.login(dto);
            UserResponseDTO response = UserResponseDTO.fromDomain(user);
            return ResponseEntity.ok(response);

        }catch (DomainException e){
            return ResponseEntity.badRequest().build();
        }
    }
    //login
}
