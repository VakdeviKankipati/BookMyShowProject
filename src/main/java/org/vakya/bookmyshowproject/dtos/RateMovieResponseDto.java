package org.vakya.bookmyshowproject.dtos;

import lombok.Data;
import org.vakya.bookmyshowproject.model.Rating;

@Data
public class RateMovieResponseDto {
    private ResponseStatus responseStatus;
    private Rating rating;
}
