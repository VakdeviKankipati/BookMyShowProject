package org.vakya.bookmyshowproject.dtos;

import lombok.Data;

@Data
public class RateMovieRequestDto {
    private long userId;
    private long movieId;
    private long rating;
}
