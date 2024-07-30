package org.vakya.bookmyshowproject.services;

import org.vakya.bookmyshowproject.exeption.MovieNotFoundException;
import org.vakya.bookmyshowproject.exeption.UserNotFoundException;
import org.vakya.bookmyshowproject.model.Rating;

public interface RatingService {
    Rating rateMovie(long userId, long movieId, long rating) throws UserNotFoundException, MovieNotFoundException;

    double getAverageRating(long movieId) throws MovieNotFoundException;
}
