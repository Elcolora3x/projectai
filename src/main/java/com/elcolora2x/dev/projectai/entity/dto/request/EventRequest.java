package com.elcolora2x.dev.projectai.entity.dto.request;

import java.time.Instant;

import com.elcolora2x.dev.projectai.entity.model.Place;
import com.elcolora2x.dev.projectai.entity.model.User;

import jakarta.validation.constraints.NotBlank;

public record EventRequest(
    @NotBlank(message = "El nombre no puede estar vacío")
    String name,
    Place place,
    Instant startDate,
    Instant endDate,
    Integer capacity,
    String metadata,
    User organizer
) {}
