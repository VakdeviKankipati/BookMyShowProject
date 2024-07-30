package org.vakya.bookmyshowproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Rating extends BaseModel{
    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    private long rating;
}
