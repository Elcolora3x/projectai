package com.elcolora2x.dev.projectai.entity.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity // 1. INDISPENSABLE: Define que esto es una tabla
@Table(name = "events") // 2. Convención: tablas en plural
@Data
@Slf4j
@NoArgsConstructor // 3. Requerido por JPA
@AllArgsConstructor
@Builder // 4. Muy útil para crear eventos: Event.builder().name("Concierto").build()
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE events SET deleted = true WHERE event_uuid = ?") // Usa el nombre de la columna ID
@SQLRestriction("deleted = false") // 5. Para que los 'findAll' no traigan los borrados
public class Event { // 6. Convención: Clases en Mayúscula (PascalCase)
 
    @Id // 7. Define la llave primaria
    @GeneratedValue(strategy = GenerationType.UUID) // Genera el UUID automáticamente
    private UUID eventUuid;

    @Column(nullable = false)
    private String name;

    // 8. Relaciones: Si 'Place' y 'User' son otras entidades, necesitas anotarlas
    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // ¡Importante para evitar bucles!
    private List<Assistance> assistances;

    @Column(nullable = false)
    private Instant startDate;

    private Instant endDate;

    private Integer capacity;

    @Column(columnDefinition = "TEXT") // 9. Para strings largos (descripciones/JSON)
    private String metadata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    // Auditoría
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Column(nullable = false)
    @Builder.Default
    private boolean deleted = Boolean.FALSE;
}