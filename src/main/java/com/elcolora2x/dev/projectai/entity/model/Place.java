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

@Entity
@Table(name = "places")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE places SET deleted = true WHERE place_uuid = ?")
@SQLRestriction("deleted = false")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID placeUuid;

    // Relación inversa: Un lugar tiene muchos eventos
    // mappedBy indica que el dueño de la relación es el campo 'place' en la clase Event
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Evita bucles infinitos en el log de Lombok con relaciones bidireccionales
    private List<Event> events;

    @Column(name = "g_maps_coords")
    private String gMapsCoords;

    @Column(nullable = false)
    private String address;

    @Column(columnDefinition = "TEXT")
    private String metadata;

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