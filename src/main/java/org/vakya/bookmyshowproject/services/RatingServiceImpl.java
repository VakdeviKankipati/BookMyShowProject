package org.vakya.bookmyshowproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vakya.bookmyshowproject.exeption.MovieNotFoundException;
import org.vakya.bookmyshowproject.exeption.UserNotFoundException;
import org.vakya.bookmyshowproject.model.Movie;
import org.vakya.bookmyshowproject.model.Rating;
import org.vakya.bookmyshowproject.model.User;
import org.vakya.bookmyshowproject.repositories.MovieRepository;
import org.vakya.bookmyshowproject.repositories.RatingRepository;
import org.vakya.bookmyshowproject.repositories.UserRepository;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{
    private MovieRepository movieRepository;
    private RatingRepository ratingRepository;
    private UserRepository userRepository;

    @Autowired
    public RatingServiceImpl(MovieRepository movieRepository,
                          RatingRepository ratingRepository,
                          UserRepository userRepository){
        this.movieRepository = movieRepository;
        this.ratingRepository=ratingRepository;
        this.userRepository=userRepository;
    }
    public Rating rateMovie(long userId, long movieId, long rating) throws UserNotFoundException, MovieNotFoundException {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        Movie movie = this.movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        // Check if user has already rated the movie, then update the rating
        Optional<Rating> optionalRating = this.ratingRepository.findByUserAndMovie(user, movie);
        if(optionalRating.isPresent()){
            Rating ratingObj = optionalRating.get();
            ratingObj.setRating(rating);
            return this.ratingRepository.save(ratingObj);
        }

        Rating ratingObj = new Rating();
        ratingObj.setMovie(movie);
        ratingObj.setUser(user);
        ratingObj.setRating(rating);
        return this.ratingRepository.save(ratingObj);
    }
    public double getAverageRating(long movieId) throws MovieNotFoundException {
        this.movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
        return this.ratingRepository.getAverageRatingForMovie(movieId);
    }
}



