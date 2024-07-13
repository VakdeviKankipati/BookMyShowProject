package org.vakya.bookmyshowproject.repositories;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vakya.bookmyshowproject.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //Ticket save(Ticket ticket);
}