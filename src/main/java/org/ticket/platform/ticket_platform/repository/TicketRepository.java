package org.ticket.platform.ticket_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ticket.platform.ticket_platform.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
}
