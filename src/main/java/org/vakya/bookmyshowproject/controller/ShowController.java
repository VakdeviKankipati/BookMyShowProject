package org.vakya.bookmyshowproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.vakya.bookmyshowproject.dtos.CreateShowRequestDTO;
import org.vakya.bookmyshowproject.dtos.CreateShowResponseDTO;
import org.vakya.bookmyshowproject.dtos.ResponseStatus;
import org.vakya.bookmyshowproject.model.Show;
import org.vakya.bookmyshowproject.services.ShowService;

@Controller
public class ShowController {
    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService){
        this.showService=showService;
    }

    public CreateShowResponseDTO createShow(CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO responseDTO = new CreateShowResponseDTO();
        try{
            Show show = showService.createShow(requestDTO.getUserId(),
                    requestDTO.getMovieId(),
                    requestDTO.getScreenId(),
                    requestDTO.getStartTime(), requestDTO.getEndTime(),
                    requestDTO.getPricingConfig(),
                    requestDTO.getFeatures()
            );
            responseDTO.setShow(show);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
