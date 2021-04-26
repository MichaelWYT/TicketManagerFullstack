package com.barclays.ticketmanager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.barclays.ticketmanager.controllers.IndexController;
import com.barclays.ticketmanager.controllers.TicketController;
import com.barclays.ticketmanager.domain.Status;
import com.barclays.ticketmanager.domain.Ticket;
import com.barclays.ticketmanager.repository.TicketRepo;
import com.barclays.ticketmanager.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TicketManagerControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TicketController ticketController = new TicketController();

	@MockBean
	TicketService ticketService;

	@MockBean
	TicketRepo ticketRepo;

	@Autowired
	ObjectMapper jsonifier;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
	}

	@Test
	void indexController() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
		mockMvc.perform(get("/isReady"))
		.andExpect(status().isOk())
		.andExpect(content().string("Is Ready :)"));
	}

	@Test
	void ticketPage() throws Exception {
		mockMvc.perform(get("/tickets"))
		.andExpect(status().isOk())
		.andExpect(content().string("Success"));
	}

	@Test
	void addTicket() throws Exception {
		String jsonRequest = "{\"data\":{\"title\":\"someone\",\"author\":\"example\",\"description\":\"something\",\"status\":\"TODO\"}}";
		Ticket savedTicket = new Ticket(1L, "someone", "example", LocalDate.now(), "something", Status.TODO);

		Mockito.when(ticketService.saveTicket(any(Ticket.class))).thenReturn(savedTicket);

		mockMvc.perform(post("/createTicket")
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonRequest))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonifier.writeValueAsString(savedTicket)));
	}

	@Test
	void getOneTicket() throws Exception {
		Ticket t1 = new Ticket(1L, "someone", "example", LocalDate.now(), "something", Status.TODO);

		Mockito.when(ticketService.getTicket(1L)).thenReturn(t1);

		mockMvc.perform(get("/getTicket/{id}", 1L))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonifier.writeValueAsString(t1)));
	}

	@Test
	void getAllTickets() throws Exception {
		Ticket t1 = new Ticket(1L, "someone", "example", LocalDate.now(), "something", Status.TODO);
		Ticket t2 = new Ticket(2L, "someone", "example", LocalDate.now(), "something", Status.INPROGRESS);
		Ticket t3 = new Ticket(3L, "someone", "example", LocalDate.now(), "something", Status.DONE);
		List<Ticket> ticketList = new ArrayList<Ticket>();
		ticketList.add(t1);	ticketList.add(t2);	ticketList.add(t3);

		Mockito.when(ticketService.getTickets()).thenReturn(ticketList);

		mockMvc.perform(get("/getAllTickets"))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonifier.writeValueAsString(ticketList)));
	}

	@Test
	void updateFullTicket() throws Exception, Throwable {
		String updateJson = "{\"data\":{\"title\":\"new\",\"author\":\"new\",\"description\":\"new\",\"status\":\"INPROGRESS\"}}";
		Ticket updatedTicket = new Ticket(1L, "new", "new", LocalDate.now(), "new", Status.INPROGRESS);
		String jsonUpdate = jsonifier.writeValueAsString(updatedTicket);

		Mockito.when(ticketService.updateTicket(eq(1L), any(Ticket.class), eq("full"))).thenReturn(updatedTicket);

		mockMvc.perform(put("/updateAll/{id}", 1L)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.characterEncoding("utf-8")
		.content(updateJson))
		// .andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().json(jsonUpdate));
	}

	@Test
	void updatePartialTicket() throws Exception {
		String updateJson = "{\"data\":{\"title\":\"new\",\"author\":\"null\",\"description\":\"new\",\"status\":\"INPROGRESS\"}}";
		Ticket updatedTicket = new Ticket(1L, "new", "old", LocalDate.now(), "new", Status.DONE);
		String jsonUpdate = jsonifier.writeValueAsString(updatedTicket);

		Mockito.when(ticketService.updateTicket(eq(1L), any(Ticket.class), eq("partial"))).thenReturn(updatedTicket);

		mockMvc.perform(patch("/updatePartial/{id}", 1L)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON)
		.content(updateJson))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonUpdate));
	}

	@Test
	void deleteTicket() throws Exception {
		Long id = 1L;

		Mockito.when(ticketService.deleteTicket(id)).thenReturn(true);

		mockMvc.perform(delete("/deleteTicket/{id}", id))
		.andExpect(status().isOk())
		.andExpect(content().string("true"));
	}
}
