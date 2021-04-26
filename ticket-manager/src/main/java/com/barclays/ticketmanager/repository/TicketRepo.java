package com.barclays.ticketmanager.repository;

import com.barclays.ticketmanager.domain.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

}
