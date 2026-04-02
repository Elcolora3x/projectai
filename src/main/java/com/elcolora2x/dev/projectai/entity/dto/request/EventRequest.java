package com.elcolora2x.dev.projectai.entity.dto.request;

import java.time.Instant;
import java.util.Optional;

import com.elcolora2x.dev.projectai.entity.model.Place;
import com.elcolora2x.dev.projectai.entity.model.User;

import jakarta.validation.constraints.NotNull;

/**
 * HTTP request body for creating or updating an event.
 *
 * @param name      event name; required
 * @param place     physical or logical place; optional when omitted or null in JSON
 * @param startDate event start instant
 * @param endDate   event end instant
 * @param capacity  maximum attendees, if applicable
 * @param metadata  free-form metadata; optional when omitted or null in JSON
 * @param organizer user organizing the event
 */
public record EventRequest(
    @NotNull
    String name,
    Optional<Place> place,
    Instant startDate,
    Instant endDate,
    Integer capacity,
    Optional<String> metadata,
    User organizer
) {
    /**
     * Ensures JSON or callers passing {@code null} for optional components are treated as {@link Optional#empty()}.
     */
    public EventRequest {
        place = place != null ? place : Optional.empty();
        metadata = metadata != null ? metadata : Optional.empty();
    }
}
