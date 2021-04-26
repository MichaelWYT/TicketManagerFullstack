package com.barclays.ticketmanager.dto;

import java.util.HashMap;

import com.barclays.ticketmanager.domain.Status;
import com.barclays.ticketmanager.domain.Ticket;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketDTO {

    private String author = null;
    private String title = null;
    private String description = null;
    private Status status = null;

    public TicketDTO() {}

    @JsonCreator
    public TicketDTO(@JsonProperty("data") HashMap<String, String> data) {
        unpackTicketJson(data);
    }

    public Ticket getTicket() {
        return new Ticket(this.author, this.title, this.description, this.status);
    }
    
    private void unpackTicketJson(HashMap<String, String> data) {
        this.author = data.get("author");
        this.title = data.get("title");
        this.description = data.get("description");
        if (data.get("status") != null) {
            switch (Status.valueOf(data.get("status"))) {
                case TODO:
                    this.status = Status.TODO;
                    break;
                case INPROGRESS:
                    this.status = Status.INPROGRESS;
                    break;
                case DONE:
                    this.status = Status.DONE;
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("{ author: %s, title: %s, description: %s, status: %s }", author, title, description, status);
    }
}
