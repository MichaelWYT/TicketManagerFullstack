package com.barclays.ticketmanager.controllers;

import java.util.List;

import com.barclays.ticketmanager.domain.Ticket;
import com.barclays.ticketmanager.dto.TicketDTO;
import com.barclays.ticketmanager.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @RequestMapping("/tickets")
    public @ResponseBody String ticketPage() {
        return "Success";
    }

    @GetMapping("/getAllTickets")
    public List<Ticket> getAllTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/getTicket/{id}")
    public Ticket getTicket(@PathVariable long id) {
        return ticketService.getTicket(id);
    }

    @PutMapping("/updateAll/{id}")
    public @ResponseBody Ticket updateFullTicket(@PathVariable("id") Long id, @RequestBody TicketDTO ticket) {
        return ticketService.updateTicket(id, ticket.getTicket(), "full");
    }

    @PatchMapping("/updatePartial/{id}")
    public @ResponseBody Ticket updatePartialTicket(@PathVariable("id") Long id, @RequestBody TicketDTO ticket) {
        return ticketService.updateTicket(id, ticket.getTicket(), "partial");
    }

    @PostMapping("/createTicket")
    public @ResponseBody Ticket addTicket(@RequestBody TicketDTO ticket) {
        return ticketService.saveTicket(ticket.getTicket());
    }

    @DeleteMapping("/deleteTicket/{id}")
    public @ResponseBody boolean deleteTicket(@PathVariable Long id) {
        return ticketService.deleteTicket(id);
    }
}
