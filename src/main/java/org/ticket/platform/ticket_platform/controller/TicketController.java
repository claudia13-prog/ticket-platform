package org.ticket.platform.ticket_platform.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.ticket.platform.ticket_platform.model.Status;
import org.ticket.platform.ticket_platform.model.Ticket;
import org.ticket.platform.ticket_platform.repository.CategoryRepository;
import org.ticket.platform.ticket_platform.repository.OperatorRepository;
import org.ticket.platform.ticket_platform.repository.TicketRepository;
import org.ticket.platform.ticket_platform.service.CategoryService;
import org.ticket.platform.ticket_platform.service.OperatorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model){
        List<Ticket> tickets = repository.findAll();
        model.addAttribute("tickets", tickets);
        return "tickets/index";
    }

    @GetMapping("/search/title")
    public String findByTitle(@RequestParam(name="title") String title, Model model){
        List<Ticket> tickets = repository.findByTitleContaining(title);
        model.addAttribute("tickets", tickets);
        return "tickets/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        //Ticket ticket =repository.findById(id).get();
        model.addAttribute("ticket", repository.findById(id).get());
        return "tickets/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("operators", operatorService.getAllOperators());
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("ticket", new Ticket());
        return "tickets/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult bindingResult, Model model){
        
        formTicket.setStatus(Status.APERTO);
        formTicket.setNotes(new ArrayList<>());
        
        if(bindingResult.hasErrors()){
            return "tickets/create";
        }

        repository.save(formTicket);
        return "redirect:/tickets";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Integer id, Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("operators", operatorService.getAllOperators());
        model.addAttribute("currentDate", LocalDate.now().toString());
        model.addAttribute("ticket", repository.findById(id).get());
        return "tickets/edit";

    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ticket") Ticket formTicket, BindingResult bindingResult, Model model){
        //formTicket.setStatus(Status.APERTO);

        if(bindingResult.hasErrors()){
            return "tickets/edit";
        }

        repository.save(formTicket);
        return "redirect:/tickets";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Ticket ticket = repository.findById(id).get();
        repository.delete(ticket);

        return "redirect:/tickets";
    }
        
}
