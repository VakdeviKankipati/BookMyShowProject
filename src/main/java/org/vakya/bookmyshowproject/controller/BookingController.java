package org.vakya.bookmyshowproject.controller;

import org.springframework.stereotype.Controller;
import org.vakya.bookmyshowproject.dtos.BookMovieRequestDto;
import org.vakya.bookmyshowproject.dtos.BookMovieResponseDto;
import org.vakya.bookmyshowproject.model.Booking;
import org.vakya.bookmyshowproject.model.Movie;
import org.vakya.bookmyshowproject.dtos.ResponseStatus;
import org.vakya.bookmyshowproject.services.BookingService;

@Controller
public class BookingController {

    private BookingService bookingService;

    private BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    public BookMovieResponseDto bookMovie(BookMovieRequestDto requestDto){
        BookMovieResponseDto responseDto = new BookMovieResponseDto();
        try{
            Booking booking = bookingService.bookMovie(
                  requestDto.getUserId(),
                  requestDto.getShowId(),
                  requestDto.getShowSeatIds()
            );
            responseDto.setBooking(booking);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);

        }

        return responseDto;
    }
}
