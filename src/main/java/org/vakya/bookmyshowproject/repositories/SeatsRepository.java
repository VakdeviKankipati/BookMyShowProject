package org.vakya.bookmyshowproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.Seat;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, Integer> {
    @Override
    List<Seat> findAllById(Iterable<Integer> integers);

    List<Seat> findAllByScreenId(int screenId);
}
