package org.ticket.platform.ticket_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ticket.platform.ticket_platform.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    public List<Ticket> findByTitleContaining(String title);
}
