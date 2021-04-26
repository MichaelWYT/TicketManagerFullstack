package com.barclays.ticketmanager.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.barclays.ticketmanager.domain.Ticket;
import com.barclays.ticketmanager.repository.TicketRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepo ticketRepo;
    
    public TicketService(TicketRepo ticketRepo) {
        super();
        this.ticketRepo = ticketRepo;
    }
    
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public Ticket getTicket(long id) {
        return ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Ticket> getTickets() {
        return ticketRepo.findAll();
    }

    public Ticket updateTicket(Long id, Ticket newTicket, String updateType) {
        Ticket existingTicket = this.ticketRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (updateType.equals("full")) {
            existingTicket.setTitle(newTicket.getTitle());
            existingTicket.setAuthor(newTicket.getAuthor());
            existingTicket.setDescription(newTicket.getDescription());
            existingTicket.setStatus(newTicket.getStatus());
        } else if (updateType.equals("partial")) {
            if (newTicket.getTitle() != null) {
                existingTicket.setTitle(newTicket.getTitle());
            }
            if (newTicket.getAuthor() != null) {
                existingTicket.setAuthor(newTicket.getAuthor());
            }
            if (newTicket.getDescription() != null) {
                existingTicket.setDescription(newTicket.getDescription());
            }
            if (newTicket.getStatus() != null) {
                existingTicket.setStatus(newTicket.getStatus());
            }
        }
        return ticketRepo.save(existingTicket);
    }

    public boolean deleteTicket(Long id) {
        try {
            ticketRepo.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
