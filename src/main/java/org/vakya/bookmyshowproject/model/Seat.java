package org.vakya.bookmyshowproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String number;

    @ManyToOne
    private SeatType seatType;

    private int rowValue;
    private int colValue;
}


/*

 1            1
Seat ----- SeatType => M:1
 M             1

 */