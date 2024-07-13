package org.vakya.bookmyshowproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.vakya.bookmyshowproject.dtos.BookTicketRequestDTO;
import org.vakya.bookmyshowproject.dtos.BookTicketResponseDTO;
import org.vakya.bookmyshowproject.dtos.ResponseStatus;
import org.vakya.bookmyshowproject.model.Ticket;
import org.vakya.bookmyshowproject.services.TicketService;

@Controller
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO requestDTO){
        BookTicketResponseDTO responseDTO = new BookTicketResponseDTO();
        try{
            Ticket ticket = ticketService.bookTicket(
                    requestDTO.getShowSeatIds(),
                    requestDTO.getUserId()
            );
            responseDTO.setTicket(ticket);
            responseDTO.setStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}

