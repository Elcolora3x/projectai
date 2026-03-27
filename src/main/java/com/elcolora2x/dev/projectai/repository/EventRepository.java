package com.elcolora2x.dev.projectai.repository;

import com.elcolora2x.dev.projectai.entity.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    // Buscar todos los eventos creados por un usuario específico
    List<Event> findByOrganizerId(UUID userId);

    // Buscar eventos en un lugar específico
    List<Event> findByPlacePlaceUuid(UUID placeUuid);

    // Buscar eventos que aún no han terminado (ejemplo de consulta por fecha)
    // List<Event> findByEndDateAfter(Instant now);
}