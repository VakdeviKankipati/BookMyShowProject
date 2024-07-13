package org.vakya.bookmyshowproject.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.ShowSeat;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    List<ShowSeat> findShowSeatsByIdIn(List<Long> ids);

    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    @Override
    ShowSeat save(ShowSeat showSeat);
}
