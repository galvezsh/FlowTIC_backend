package com.galvezsh.flowtic.service;

import com.galvezsh.flowtic.mappers.TicketMapper;
import com.galvezsh.flowtic.model.dto.TicketDto;
import com.galvezsh.flowtic.model.entity.Ticket;
import com.galvezsh.flowtic.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer that contains the business logic for Tickets.
 * It communicates with the repository and is used by the controller.
 */
@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    //GET ALL
    public List<TicketDto> getAllTickets() {
        return repository.findAll().stream().map( TicketMapper::toDTO ).toList();
    }

    // GET ONE
    public Optional<TicketDto> getTicketById(Long id) {
        return repository.findById(id).map( TicketMapper::toDTO );
    }

    // POST ONE
    public TicketDto createTicket(TicketDto dto) {
        Ticket ticket = TicketMapper.toEntity( dto );
        Ticket saved = repository.save( ticket );

        return TicketMapper.toDTO(saved);
    }

    // PUT ONE
    public Optional<TicketDto> updateTicket(Long id, TicketDto dto) {
        return repository.findById(id).map(existing -> {
                existing.setTitle(dto.getTitle());
                existing.setDescription(dto.getDescription());
                existing.setStatus(dto.getStatus());
                existing.setAssignedTo(dto.getAssignedTo());
                existing.setPriority(dto.getPriority());
                existing.setSolution(dto.getSolution());
                Ticket updated = repository.save(existing);

                return TicketMapper.toDTO(updated);
            });
    }

    // DELETE ONE
    public boolean deleteTicket(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
