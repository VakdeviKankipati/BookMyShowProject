package org.vakya.bookmyshowproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vakya.bookmyshowproject.dtos.BookMovieRequestDto;
import org.vakya.bookmyshowproject.dtos.BookMovieResponseDto;
import org.vakya.bookmyshowproject.model.Booking;
import org.vakya.bookmyshowproject.model.Movie;
import org.vakya.bookmyshowproject.dtos.ResponseStatus;
import org.vakya.bookmyshowproject.services.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;

    private BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @PostMapping("/book")
    public BookMovieResponseDto bookMovie(@RequestBody BookMovieRequestDto requestDto){
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
