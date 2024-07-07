package org.vakya.bookmyshowproject.controller;

import org.springframework.stereotype.Controller;
import org.vakya.bookmyshowproject.dtos.*;
import org.vakya.bookmyshowproject.model.User;
import org.vakya.bookmyshowproject.services.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    public SignupUserResponseDTO signUp(SignUpUserRequestDTO requestDto){
        SignupUserResponseDTO responseDto = new SignupUserResponseDTO();
        try{
            User user = userService.signUp(
                    requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            responseDto.setUser(user);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);

        }
        System.out.println("controller");
        return responseDto;
    }

    public LoginResponseDto login(LoginRequestDto requestDto){
        LoginResponseDto responseDto = new LoginResponseDto();
        try{
            Boolean ans = userService.login(
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            responseDto.setLoggedIn(ans);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }



}
