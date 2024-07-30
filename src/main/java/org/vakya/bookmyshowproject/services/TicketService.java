package org.vakya.bookmyshowproject.services;

import org.vakya.bookmyshowproject.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(List<Long> showSeatIds, long userId) throws Exception;
}
