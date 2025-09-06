package com.galvezsh.flowtic.mappers;

import com.galvezsh.flowtic.model.dto.TicketDto;
import com.galvezsh.flowtic.model.entity.Ticket;

public class TicketMapper {

    public static TicketDto toDTO(Ticket ticket) {
        if (ticket == null) return null;

        return TicketDto.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())
                .assignedTo(ticket.getAssignedTo())
                .solution(ticket.getSolution())
                .createdAt(ticket.getCreatedAt())
                .modifiedAt(ticket.getModifiedAt())
                .build();
    }

    public static Ticket toEntity(TicketDto dto) {
        if (dto == null) return null;

        return Ticket.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .assignedTo(dto.getAssignedTo())
                .solution(dto.getSolution())
                .createdAt(dto.getCreatedAt())
                .modifiedAt(dto.getModifiedAt())
                .build();
    }
}

