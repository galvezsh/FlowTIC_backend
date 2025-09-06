package com.galvezsh.flowtic.controller;

import com.galvezsh.flowtic.model.dto.PagedResponse;
import com.galvezsh.flowtic.model.dto.TicketDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galvezsh.flowtic.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Tickets.
 * Exposes HTTP endpoints to create, read and delete tickets.
 */
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    /**
     * GET /api/tickets
     * Retrieves all tickets from the system.
     *
     * Success: Returns a list of TicketDto objects (HTTP 200).
     * Failure: If the database is empty, it will return an empty list (still HTTP 200).
     */
//    @GetMapping
//    public List<TicketDto> getAll() {
//        return service.getAllTickets();
//    }

    @GetMapping
    public ResponseEntity<PagedResponse<TicketDto>> getAllPaged(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
    ) {

        String baseUrl = request.getRequestURL().toString();
        PagedResponse<TicketDto> response = service.getPagedTickets(page, size, baseUrl);
        return ResponseEntity.ok(response);
    }


    /**
     * GET /api/tickets/{id}
     * Retrieves a single ticket by its unique ID.
     *
     * Success: Returns the TicketDto with HTTP 200 OK.
     * Failure: If the ticket does not exist, returns HTTP 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable Long id) {
        return service.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/tickets
     * Creates a new ticket in the system.
     *
     * Success: Returns the created TicketDto with HTTP 201 Created.
     * Failure: If the request body is invalid (e.g., missing required fields, invalid JSON),
     *          it may return HTTP 400 Bad Request.
     */
    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto dto) {
        TicketDto created = service.createTicket(dto);
        return ResponseEntity.status(201).body(created);
    }

    /**
     * PUT /api/tickets/{id}
     * Updates an existing ticket by ID with the provided data.
     *
     * Success: Returns the updated TicketDto with HTTP 200 OK.
     * Failure:
     *   - If the ticket does not exist, returns HTTP 404 Not Found.
     *   - If the request body is invalid, it may return HTTP 400 Bad Request.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable Long id, @RequestBody TicketDto dto) {
        return service.updateTicket(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /api/tickets/{id}
     * Deletes a ticket by its unique ID.
     *
     * Success: Returns HTTP 204 No Content if deletion was successful.
     * Failure: Returns HTTP 404 Not Found if the ticket does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (service.deleteTicket(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

}

