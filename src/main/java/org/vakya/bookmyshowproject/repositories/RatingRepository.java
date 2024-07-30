package org.vakya.bookmyshowproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.Movie;
import org.vakya.bookmyshowproject.model.Rating;
import org.vakya.bookmyshowproject.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.movie.id = ?1")
    Double getAverageRatingForMovie(Long movieId);


    Optional<Rating> findByUserAndMovie(User user, Movie movie);

}
