package org.vakya.bookmyshowproject.dtos;

import lombok.Data;
import org.springframework.data.util.Pair;
import org.vakya.bookmyshowproject.model.Feature;
import org.vakya.bookmyshowproject.model.SeatType;

import java.util.Date;
import java.util.List;

@Data
public class CreateShowRequestDTO {
    private int movieId;
    private int userId;
    private int screenId;
    private Date startTime;
    private Date endTime;
    private List<Feature> features;
    private List<Pair<SeatType, Double>> pricingConfig;
}
