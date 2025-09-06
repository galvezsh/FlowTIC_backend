package com.galvezsh.flowtic.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galvezsh.flowtic.model.entity.Ticket.Priority;
import com.galvezsh.flowtic.model.entity.Ticket.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Ticket entity.
 * Used to expose ticket data in API responses without exposing JPA internals.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private String assignedTo;
    private String solution;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedAt;
}
