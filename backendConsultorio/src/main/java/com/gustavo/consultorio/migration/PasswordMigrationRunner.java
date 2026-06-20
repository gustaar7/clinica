package com.gustavo.consultorio.migration;

import com.gustavo.consultorio.entity.PacienteEntity;
import com.gustavo.consultorio.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PasswordMigrationRunner implements CommandLineRunner {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<PacienteEntity> all = pacienteRepository.findAll();
        boolean changed = false;
        for (PacienteEntity p : all) {
            String s = p.getSenha();
            if (s == null) continue;
            if (s.startsWith("$2a$") || s.startsWith("$2b$") || s.startsWith("$2y$")) {
                continue; // already hashed
            }
            // re-encode plain password
            p.setSenha(passwordEncoder.encode(s));
            pacienteRepository.save(p);
            changed = true;
        }
        if (changed) System.out.println("PasswordMigrationRunner: migrated existing plain passwords to bcrypt.");
    }
}
