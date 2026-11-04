package com.movieting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movieting.exception.TicketNotFoundException;
import com.movieting.model.Ticket;
import com.movieting.repository.TicketRepository;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        ticket.setTicketId(null);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));
    }

    public Ticket updateTicket(Long ticketId, Ticket updatedTicket) {
        Ticket ticket = getTicketById(ticketId);
        ticket.setMovieName(updatedTicket.getMovieName());
        ticket.setCustomerName(updatedTicket.getCustomerName());
        ticket.setSeatNumber(updatedTicket.getSeatNumber());
        ticket.setTicketPrice(updatedTicket.getTicketPrice());
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketId) {
        ticketRepository.delete(getTicketById(ticketId));
    }
}
