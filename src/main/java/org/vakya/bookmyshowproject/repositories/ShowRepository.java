package org.vakya.bookmyshowproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.Show;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    //@Override
    //Optional<Show> findById(Long showId);
}
