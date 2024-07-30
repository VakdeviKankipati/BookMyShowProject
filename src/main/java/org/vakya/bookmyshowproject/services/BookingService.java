package org.vakya.bookmyshowproject.services;

import org.vakya.bookmyshowproject.exeption.ShowNotFoundException;
import org.vakya.bookmyshowproject.exeption.UserNotFoundException;
import org.vakya.bookmyshowproject.model.Booking;

import java.util.List;

public interface BookingService {
    Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException;
}
