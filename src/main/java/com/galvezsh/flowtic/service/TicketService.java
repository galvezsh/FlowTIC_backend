package com.galvezsh.flowtic.service;

import com.galvezsh.flowtic.mappers.TicketMapper;
import com.galvezsh.flowtic.model.dto.PagedResponse;
import com.galvezsh.flowtic.model.dto.TicketDto;
import com.galvezsh.flowtic.model.entity.Ticket;
import com.galvezsh.flowtic.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer that contains the business logic for Tickets.
 * It communicates with the repository and is used by the controller.
 */
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;

//    //GET ALL
//    public List<TicketDto> getAllTickets() {
//        return repository.findAll().stream().map( TicketMapper::toDTO ).toList();
//    }

    // GET PAGED TICKETS
    public PagedResponse<TicketDto> getPagedTickets( int page, int size, String baseUrl ) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        Page<Ticket> ticketPage = repository.findAll(pageable);

        List<TicketDto> ticketDtos = ticketPage.stream().map( TicketMapper::toDTO ).toList();

        String next = ticketPage.hasNext() ? baseUrl + "?page=" + (page + 1) + "&size=" + size : null;
        String prev = ticketPage.hasPrevious() ? baseUrl + "?page=" + (page - 1) + "&size=" + size : null;

        return PagedResponse.<TicketDto>builder()
                .info(PagedResponse.PageInfo.builder()
                        .count(ticketPage.getTotalElements())
                        .pages(ticketPage.getTotalPages())
                        .next(next)
                        .prev(prev)
                        .build())
                .results(ticketDtos)
                .build();
    }

    // GET ONE
    public Optional<TicketDto> getTicketById( Long id ) {
        return repository.findById(id).map( TicketMapper::toDTO );
    }

    // POST ONE
    public TicketDto createTicket( TicketDto dto ) {
        Ticket ticket = TicketMapper.toEntity( dto );
        Ticket saved = repository.save( ticket );

        return TicketMapper.toDTO(saved);
    }

    // PUT ONE
    public Optional<TicketDto> updateTicket( Long id, TicketDto dto ) {
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
    public boolean deleteTicket( Long id ) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
