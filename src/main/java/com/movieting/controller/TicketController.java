package com.movieting.controller;

import com.movieting.model.Ticket;
import com.movieting.service.TicketService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.createTicket(ticket);

        System.out.println("Ticket created successfully");
        System.out.println("Ticket ID: " + savedTicket.getTicketId());
        System.out.println("Movie Name: " + savedTicket.getMovieName());
        System.out.println("Customer Name: " + savedTicket.getCustomerName());
        System.out.println("Seat Number: " + savedTicket.getSeatNumber());
        System.out.println("Ticket Price: " + savedTicket.getTicketPrice());

        return ResponseEntity.created(URI.create("/api/tickets/" + savedTicket.getTicketId()))
                .body(savedTicket);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    public Ticket getTicketById(@PathVariable Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    @PutMapping("/{ticketId}")
    public Ticket updateTicket(@PathVariable Long ticketId, @Valid @RequestBody Ticket ticket) {
        return ticketService.updateTicket(ticketId, ticket);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
