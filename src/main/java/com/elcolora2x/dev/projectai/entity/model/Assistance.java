package com.elcolora2x.dev.projectai.entity.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.elcolora2x.dev.projectai.entity.enums.PersonIdType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "assistances")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE assistances SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Assistance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String email;

    @Column(name = "person_id", nullable = false)
    private String personId;

    @Enumerated(EnumType.STRING) // Guarda el nombre del Enum (ej: 'DNI', 'PASSPORT')
    @Column(name = "person_id_type", nullable = false)
    private PersonIdType personIdType;

    // En asistencia, created es vital; updated y deleted suelen ser menos comunes 
    // pero los mantenemos por consistencia con tu esquema
    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    @Builder.Default
    private boolean deleted = Boolean.FALSE;
}