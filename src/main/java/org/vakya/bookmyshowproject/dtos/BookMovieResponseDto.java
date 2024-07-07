package org.vakya.bookmyshowproject.dtos;

import lombok.Getter;
import lombok.Setter;
import org.vakya.bookmyshowproject.model.Booking;

@Getter
@Setter
public class BookMovieResponseDto {
    private Booking booking;
    private ResponseStatus responseStatus;
}
