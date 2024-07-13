package org.vakya.bookmyshowproject.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequestDTO {
    private List<Long> showSeatIds;
    private long userId;
}
