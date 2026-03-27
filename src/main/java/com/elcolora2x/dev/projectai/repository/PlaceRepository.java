package com.elcolora2x.dev.projectai.repository;

import com.elcolora2x.dev.projectai.entity.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, UUID> {
    List<Place> findByAdressContainingIgnoreCase(String adress);
}