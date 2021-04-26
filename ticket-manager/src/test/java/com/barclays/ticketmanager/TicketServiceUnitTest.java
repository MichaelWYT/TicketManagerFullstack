package com.barclays.ticketmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.barclays.ticketmanager.domain.Status;
import com.barclays.ticketmanager.domain.Ticket;
import com.barclays.ticketmanager.repository.TicketRepo;
import com.barclays.ticketmanager.service.TicketService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class TicketServiceUnitTest {
    
    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepo ticketRepo;
    
    @Test
    void addTicketSuccess() {
        Ticket dummyTicket1 = new Ticket("author1", "title1", "description1", Status.DONE);
        Ticket savedDummy1 = new Ticket(1L, "author1", "title1", LocalDate.now(), "description1", Status.DONE);

        Mockito.when(ticketRepo.save(dummyTicket1)).thenReturn(savedDummy1);

        Assertions.assertThat(ticketService.saveTicket(dummyTicket1)).isEqualTo(savedDummy1);

        Mockito.verify(ticketRepo, Mockito.times(1)).save(dummyTicket1);
    }

    @Test
    void getSingleTicket() {
        Ticket savedDummy1 = new Ticket(1L, "author1", "title1", LocalDate.now(), "description1", Status.TODO);

        Mockito.when(ticketRepo.findById(1L)).thenReturn(Optional.of(savedDummy1));

        Assertions.assertThat(ticketService.getTicket(1L)).isEqualTo(savedDummy1);

        Mockito.verify(ticketRepo, Mockito.times(1)).findById(1L);
    }

    @Test
    void getAllTickets() {
        Ticket savedDummy1 = new Ticket(1L, "author1", "title1", LocalDate.now(), "description1", Status.TODO);
        Ticket savedDummy2 = new Ticket(2L, "author2", "title2", LocalDate.now(), "description2", Status.INPROGRESS);
        Ticket savedDummy3 = new Ticket(3L, "author3", "title3", LocalDate.now(), "description3", Status.DONE);

        List<Ticket> savedTickets = new ArrayList();
        savedTickets.add(savedDummy1);
        savedTickets.add(savedDummy2);
        savedTickets.add(savedDummy3);

        Mockito.when(ticketRepo.findAll()).thenReturn(savedTickets);

        Assertions.assertThat(ticketService.getTickets()).isEqualTo(savedTickets);
        
        Mockito.verify(ticketRepo, Mockito.times(1)).findAll();
    }

    @Test
    void updateTicketFull() {
        Ticket savedDummy1 = new Ticket(1L, "author", "title", LocalDate.now(), "description", Status.INPROGRESS);
        Ticket updateTicket1 = new Ticket(1L, "newAuthor", "newTitle", LocalDate.now(), "newDesciption", Status.DONE);

        Mockito.when(ticketRepo.findById(1L)).thenReturn(Optional.of(savedDummy1));
        Mockito.when(ticketRepo.save(savedDummy1)).thenReturn(updateTicket1);

        Assertions.assertThat(ticketService.updateTicket(1L, updateTicket1, "full")).isEqualTo(updateTicket1);

        Mockito.verify(ticketRepo, Mockito.times(1)).findById(1L);
        Mockito.verify(ticketRepo, Mockito.times(1)).save(updateTicket1);
    }

    @Test
    void updateTicketPartial() {
        Ticket savedDummy1 = new Ticket(1L, "same author", "title", LocalDate.now(), "description", Status.INPROGRESS);
        Ticket savedDummy2 = new Ticket(2L, "same author", "title", LocalDate.now(), "description", Status.INPROGRESS);

        Ticket updateTicketPartial1 = new Ticket(1L, "new author", "newTitle", LocalDate.now(), null, Status.DONE);
        Ticket updateTicketPartial2 = new Ticket(2L, null, null, LocalDate.now(), "new desciption", null);

        Ticket savedUpdated1 = new Ticket(1L, "same author", "newTitle", LocalDate.now(), "description", Status.DONE);
        Ticket savedUpdated2 = new Ticket(2L, "same author", "title", LocalDate.now(), "new desciption", Status.INPROGRESS);

        Mockito.when(ticketRepo.findById(1L)).thenReturn(Optional.of(savedDummy1));
        Mockito.when(ticketRepo.save(savedDummy1)).thenReturn(savedUpdated1);
        Mockito.when(ticketRepo.findById(2L)).thenReturn(Optional.of(savedDummy2));
        Mockito.when(ticketRepo.save(savedDummy2)).thenReturn(savedUpdated2);

        Assertions.assertThat(ticketService.updateTicket(1L, updateTicketPartial1, "partial")).isEqualTo(savedUpdated1);
        Assertions.assertThat(ticketService.updateTicket(2L, updateTicketPartial2, "partial")).isEqualTo(savedUpdated2);

        Mockito.verify(ticketRepo, Mockito.times(1)).findById(1L);
        Mockito.verify(ticketRepo, Mockito.times(1)).save(savedDummy1);
        Mockito.verify(ticketRepo, Mockito.times(1)).findById(2L);
        Mockito.verify(ticketRepo, Mockito.times(1)).save(savedDummy2);
    }

    @Test
    void deleteTicket() {
        Mockito.doNothing().when(ticketRepo).deleteById(1L);

        Assertions.assertThat(ticketService.deleteTicket(1L)).isTrue();
        
        Mockito.verify(ticketRepo, Mockito.times(1)).deleteById(1L);
    }
}
