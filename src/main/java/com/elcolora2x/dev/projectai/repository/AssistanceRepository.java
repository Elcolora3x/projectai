package com.elcolora2x.dev.projectai.repository;

import com.elcolora2x.dev.projectai.entity.model.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AssistanceRepository extends JpaRepository<Assistance, UUID> {
    // Listar todos los asistentes a un evento puntual
    List<Assistance> findByEventEventUuid(UUID eventUuid);

    // Verificar si una persona (por DNI/Email) ya está inscrita a un evento
    boolean existsByEventEventUuidAndEmail(UUID eventUuid, String email);
    boolean existsByEventEventUuidAndPersonId(UUID eventUuid, String personId);

    // Contar cuánta gente hay inscrita para comparar con la capacidad del evento
    long countByEventEventUuid(UUID eventUuid);
}