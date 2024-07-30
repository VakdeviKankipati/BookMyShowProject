package org.vakya.bookmyshowproject.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.vakya.bookmyshowproject.model.*;
import org.vakya.bookmyshowproject.repositories.ShowSeatRepository;
import org.vakya.bookmyshowproject.repositories.TicketRepository;
import org.vakya.bookmyshowproject.repositories.UserRepository;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{
    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;
    private UserRepository userRepository;

    public TicketServiceImpl(ShowSeatRepository showSeatRepository,
                         TicketRepository ticketRepository,
                         UserRepository userRepository) {
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(List<Long> showSeatIds, long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

        List<ShowSeat> showSeats = showSeatRepository.findShowSeatsByIdIn(showSeatIds);
        if (showSeats.size() != showSeatIds.size()) {
            throw new Exception("Invalid seat id(s)");
        }

        Show show = showSeats.stream().map(ShowSeat::getShow).findFirst().orElseThrow(() -> new Exception("Show not found"));

        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new Exception("Seat " + showSeat.getId() + " not available");
            }
        }

        showSeats.forEach(showSeat -> showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED));
        showSeatRepository.saveAll(showSeats);

        Ticket ticket = new Ticket();
        //ticket.setUser(user);
        ticket.setSeats(showSeats.stream().map(ShowSeat::getSeat).toList());
        ticket.setShow(show);
        ticket.setTimeOfBooking(new Date());
        ticket.setStatus(TicketStatus.UNPAID);


        return ticketRepository.save(ticket);

    }
}
