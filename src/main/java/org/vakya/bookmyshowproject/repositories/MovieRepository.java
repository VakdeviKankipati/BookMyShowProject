package org.vakya.bookmyshowproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
