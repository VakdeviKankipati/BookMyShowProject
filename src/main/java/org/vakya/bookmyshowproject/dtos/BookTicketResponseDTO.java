package org.vakya.bookmyshowproject.dtos;

import lombok.Data;
import org.vakya.bookmyshowproject.model.Ticket;

@Data
public class BookTicketResponseDTO {

    private ResponseStatus status;
    private Ticket ticket;
}
