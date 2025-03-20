package org.ticket.platform.ticket_platform.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ticket.platform.ticket_platform.model.Ticket;
import org.ticket.platform.ticket_platform.repository.TicketRepository;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository repository;

    @GetMapping
    public String index(Model model){
        List<Ticket> tickets = repository.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets/index";
    }
    
}
