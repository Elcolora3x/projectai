package com.elcolora2x.dev.projectai.repository;

import com.elcolora2x.dev.projectai.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Fundamental para el login con JWT más adelante
    Optional<User> findByEmail(String email);
    
    // Para validar si un email ya existe antes de registrar
    boolean existsByEmail(String email);
}