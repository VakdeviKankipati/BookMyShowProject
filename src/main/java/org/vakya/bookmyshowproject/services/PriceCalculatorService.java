package org.vakya.bookmyshowproject.services;

import org.springframework.stereotype.Service;
import org.vakya.bookmyshowproject.model.Show;
import org.vakya.bookmyshowproject.model.ShowSeat;
import org.vakya.bookmyshowproject.model.ShowSeatType;
import org.vakya.bookmyshowproject.repositories.ShowSeatTypeRepository;

import java.util.List;

@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository;

    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository=showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Show show) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;

        for (ShowSeat showSeat : showSeats){
            for (ShowSeatType showSeatType : showSeatTypes){
                if (showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount = amount + showSeatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
