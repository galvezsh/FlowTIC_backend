package com.galvezsh.flowtic.repository;

import com.galvezsh.flowtic.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Ticket entity.
 * Extends JpaRepository to provide CRUD operations without SQL code.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {  }
