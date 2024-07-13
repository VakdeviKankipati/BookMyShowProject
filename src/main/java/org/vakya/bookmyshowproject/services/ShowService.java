package org.vakya.bookmyshowproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.vakya.bookmyshowproject.exeption.*;
import org.vakya.bookmyshowproject.model.*;
import org.vakya.bookmyshowproject.repositories.*;

import java.util.*;

@Service
public class ShowService {
    private MovieRepository movieRepository;
    private ScreenRepository screenRepository;
    private SeatsRepository seatsRepository;
    private ShowSeatTypeRepository seatTypeShowRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;

    @Autowired
    public ShowService(MovieRepository movieRepository,
                           ShowRepository showRepository,
                           SeatsRepository seatsRepository,
                           ShowSeatTypeRepository seatTypeShowRepository,
                           ScreenRepository screenRepository,
                           ShowSeatRepository showSeatRepository,
                           UserRepository userRepository){
        this.movieRepository=movieRepository;
        this.screenRepository=screenRepository;
        this.showRepository=showRepository;
        this.seatsRepository=seatsRepository;
        this.showSeatRepository=showSeatRepository;
        this.seatTypeShowRepository=seatTypeShowRepository;
        this.userRepository=userRepository;
    }


    public Show createShow(long userId, int movieId, int screenId, Date startTime, Date endTime, List<Pair<SeatType, Double>> pricingConfig, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException, UserNotFoundException, UnAuthorizedAccessException {
        Movie movie = movieRepository.findById(movieId).orElseThrow(()-> new MovieNotFoundException("movie not found"));
        Screen screen = screenRepository.findById(screenId).orElseThrow(()-> new ScreenNotFoundException("screen not found"));
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
        if(!user.getUserType().equals(UserType.ADMIN)){
            throw new UnAuthorizedAccessException("only admin can access");
        }
        Set<Feature> featureSet = new HashSet<>(screen.getFeatures());
        for(Feature feature : features){
            if(!featureSet.contains(feature)){
                throw new FeatureNotSupportedByScreen("feature is not supported by screen");
            }
        }
        Date now = new Date();
        if(startTime.before(now) ){
            throw new InvalidDateException("Start time cannot be before current time");
        }
        if(endTime.before(startTime)){
            throw new InvalidDateException("End time cannot be before start time");
        }
        Show show = new Show();
        show.setMovie(movie);
        show.setFeatures(features);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setScreen(screen);
        show = showRepository.save(show);

        List<Seat> seats = seatsRepository.findAllByScreenId(screenId);
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat: seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(show);
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeats.add(showSeat);
        }
        showSeatRepository.saveAll(showSeats);

        // Store Pricing information
        for(Pair<SeatType, Double> pair: pricingConfig){
            SeatType seatType = pair.getFirst();
            Double price = pair.getSecond();
            ShowSeatType  seatTypeShow = new ShowSeatType();
            seatTypeShow.setSeatType(seatType);
            seatTypeShow.setShow(show);
            seatTypeShow.setPrice(price);
            seatTypeShowRepository.save(seatTypeShow);
        }

        return show;
    }
}
