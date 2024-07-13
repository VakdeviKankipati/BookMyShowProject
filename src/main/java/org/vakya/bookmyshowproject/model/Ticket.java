package org.vakya.bookmyshowproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "ticket")
@EqualsAndHashCode(callSuper = false)
public class Ticket extends BaseModel{
    @ManyToOne
    private Show show;

    @OneToMany
    private List<Seat> seats;

    private Date timeOfBooking;

    @ManyToOne
    private User user;

    private TicketStatus status;
}

