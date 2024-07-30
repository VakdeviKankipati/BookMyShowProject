package org.vakya.bookmyshowproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vakya.bookmyshowproject.dtos.*;
import org.vakya.bookmyshowproject.dtos.ResponseStatus;
import org.vakya.bookmyshowproject.model.Rating;
import org.vakya.bookmyshowproject.services.RatingService;


@RestController
@RequestMapping("/rating")
public class RatingsController {
    private RatingService ratingsService;

    @Autowired
    public RatingsController(RatingService ratingsService){
        this.ratingsService=ratingsService;
    }


    @PostMapping("/rate")
    public RateMovieResponseDto rateMovie(@RequestBody RateMovieRequestDto requestDto){
        RateMovieResponseDto responseDto = new RateMovieResponseDto();
        try{
            Rating rating = ratingsService.rateMovie(
                    requestDto.getUserId(),
                    requestDto.getMovieId(),
                    requestDto.getRating()
            );
            responseDto.setRating(rating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
    @GetMapping("/average")
    public GetAverageMovieResponseDto getAverageMovieRating(@RequestBody GetAverageMovieRequestDto requestDto){
        GetAverageMovieResponseDto responseDto = new GetAverageMovieResponseDto();
        try{
            double averageRating = ratingsService.getAverageRating(
                    requestDto.getMovieId()
            );
            responseDto.setAverageRating(averageRating);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
