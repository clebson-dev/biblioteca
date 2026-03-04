package com.api.biblioteca.config;

import com.api.biblioteca.model.Usuario;
import com.api.biblioteca.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@RequiredArgsConstructor
@Slf4j // Anotação do Lombok para usarmos o log.info() profissional
public class AdminSeederConfig implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Vai buscar os valores ao application.properties.
    @Value("${admin.default.username}")
    private String adminUsername;

    @Value("${admin.default.password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        // Verifica na base de dados se o utilizador já existe usando a variável
        if (usuarioRepository.findByUsuario(adminUsername).isEmpty()) {

            Usuario admin = new Usuario();
            admin.setNome("Administrador do Sistema");
            admin.setUsuario(adminUsername);

            // Usa a senha vinda do ficheiro de configuração (ou variável de ambiente)
            admin.setSenha(passwordEncoder.encode(adminPassword));

            usuarioRepository.save(admin);
            log.info("Administrador padrão criado com sucesso.");
        } else {
            log.info("O administrador padrão já existe na base de dados.");
        }
    }
}