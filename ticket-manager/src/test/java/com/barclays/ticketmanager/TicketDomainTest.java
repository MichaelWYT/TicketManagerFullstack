package com.barclays.ticketmanager;

import java.time.LocalDate;

import com.barclays.ticketmanager.domain.Status;
import com.barclays.ticketmanager.domain.Ticket;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class TicketDomainTest {

    @Test
    void settersTest() {
        Ticket ticket = new Ticket();
        ticket.setAuthor("author");
        ticket.setDate(LocalDate.now());
        ticket.setDescription("description");
        ticket.setId(1L);
        ticket.setTitle("title");
        ticket.setStatus(Status.TODO);

        Ticket ticket2 = new Ticket(1L, "author", "title", LocalDate.now(), "description", Status.TODO);

        Assertions.assertThat(ticket).isEqualTo(ticket2);
    }

    @Test
    void ticketEquality() {
        Ticket baseTicket = new Ticket(1L, "author", "title", LocalDate.now(), "description", Status.TODO);
        Ticket sameTicket = new Ticket(1L, "author", "title", LocalDate.now(), "description", Status.TODO);
        Ticket nullAuthor = new Ticket(1L, null, "title", LocalDate.now(), "description", Status.TODO);
        Ticket nullDate = new Ticket(1L, "author", "title", null, "description", Status.TODO);
        Ticket diffDate = new Ticket(1L, "author", "title", LocalDate.now().plusDays(1), "description", Status.TODO);
        Ticket nullDescription = new Ticket(1L, "author", "title", LocalDate.now(), null, Status.TODO);
        Ticket nullStatus = new Ticket(1L, "author", "title", LocalDate.now(), "description", null);
        Ticket diffStatus = new Ticket(1L, "author", "title", LocalDate.now(), "description", Status.DONE);
        Ticket nullTitle = new Ticket(1L, "author", null, LocalDate.now(), "description", Status.TODO);
        Ticket diffTitle = new Ticket(1L, "author", "Hello World", LocalDate.now(), "description", Status.TODO);

        Assertions.assertThat(baseTicket.equals(sameTicket)).isTrue();
        Assertions.assertThat(baseTicket.equals(baseTicket)).isTrue();
        Assertions.assertThat(baseTicket.equals(null)).isFalse();
        Assertions.assertThat(baseTicket.equals("")).isFalse();
        Assertions.assertThat(nullAuthor.equals(baseTicket)).isFalse();
        Assertions.assertThat(nullDate.equals(baseTicket)).isFalse();
        Assertions.assertThat(diffDate.equals(baseTicket)).isFalse();
        Assertions.assertThat(nullDescription.equals(baseTicket)).isFalse();
        Assertions.assertThat(nullStatus.equals(baseTicket)).isFalse();
        Assertions.assertThat(diffStatus.equals(baseTicket)).isFalse();
        Assertions.assertThat(nullTitle.equals(baseTicket)).isFalse();
        Assertions.assertThat(diffTitle.equals(baseTicket)).isFalse();
    }

    @Test
    void testingToString() {
        Ticket ticket1 = new Ticket(1L, "author", "title", LocalDate.now(), "description", Status.TODO);
        Ticket ticket2 = new Ticket(1L, "author", "title", LocalDate.now(), "description", Status.TODO);

        Assertions.assertThat(ticket1.toString().equals(ticket2.toString())).isTrue();
    }
}
