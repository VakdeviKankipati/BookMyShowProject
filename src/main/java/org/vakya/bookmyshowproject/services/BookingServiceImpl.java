package org.vakya.bookmyshowproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vakya.bookmyshowproject.exeption.ShowNotFoundException;
import org.vakya.bookmyshowproject.exeption.UserNotFoundException;
import org.vakya.bookmyshowproject.model.*;
import org.vakya.bookmyshowproject.repositories.ShowRepository;
import org.vakya.bookmyshowproject.repositories.ShowSeatRepository;
import org.vakya.bookmyshowproject.repositories.ShowSeatTypeRepository;
import org.vakya.bookmyshowproject.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private PriceCalculatorService priceCalculatorService;

    public BookingServiceImpl(ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository,
                          UserRepository userRepository,
                          PriceCalculatorService priceCalculatorService){
        this.showRepository=showRepository;
        this.showSeatRepository=showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculatorService=priceCalculatorService;
    }


    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException {
        /*
        1. Get the user with the userId.
        2. Get the show with the showId.
        3. Get the list of show seats with the showSeatIds.
        4. Check if all the seats are available or not.
        5. If yes, proceed with the booking.
        6. If not, throw an exception.
        ----------TAKE A LOCK---------
        7. Check if all the seats are available or not.
        8. Change the seat status to BLOCKED.
        ----------RELEASE THE LOCK----------
        9. Create the booking and move the payment page.
         */


        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with id" + userId + " doesn't exist");
        }

        User user = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);

        if (optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Show with id: " + showId + " doesn't exist");
        }

        Show show = optionalShow.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for (ShowSeat showSeat : showSeats) {
            if (!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) {
                throw new RuntimeException("ShowSeat with id" + showSeat.getId() + " isn't available");
            }
        }

        for (ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            //change the status in DB as well.
            showSeatRepository.save(showSeat);
        }

        //Create the booking and Move to the payment page.
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShowSeats(showSeats);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setCreatedAt(new Date());
        booking.setAmount(priceCalculatorService.calculatePrice(showSeats, show));

        //Save booking in the DB.

        return booking;
    }
}
