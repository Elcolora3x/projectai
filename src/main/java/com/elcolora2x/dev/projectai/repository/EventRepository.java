package com.elcolora2x.dev.projectai.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elcolora2x.dev.projectai.entity.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

}
