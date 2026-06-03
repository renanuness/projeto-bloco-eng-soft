package com.renan.projeto_bloco.domain.services;

import com.renan.projeto_bloco.application.dto.UserLoginDTO;
import com.renan.projeto_bloco.application.dto.UserRequestDTO;
import com.renan.projeto_bloco.domain.exceptions.DomainException;
import com.renan.projeto_bloco.domain.models.User;
import com.renan.projeto_bloco.domain.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository usuarioRepository;


    //private final PasswordEncoder passwordEncoder;

    public User cadastrar(UserRequestDTO dto) throws DomainException {
        // Verifica se email já existe
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new DomainException("Email já cadastrado");
        }

        // Cria o usuário (aplica regras de domínio)
        User usuario = User.create(
                dto.nome(),
                dto.email(),
                dto.senha()
        );

        // Criptografa a senha (infraestrutura, mas ok ficar aqui)
        //usuario.criptografarSenha(passwordEncoder);

        return usuarioRepository.save(usuario);
    }

    public User login(@Valid UserLoginDTO dto) throws DomainException {
        User user = usuarioRepository.findByEmail(dto.email()).orElseThrow(()->new DomainException("Login inválido"));

        if(user.getPassword() == dto.password()) {
            return user;
        }

        throw new DomainException("Login inválido");
    }
}
