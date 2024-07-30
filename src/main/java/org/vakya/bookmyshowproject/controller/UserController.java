package org.vakya.bookmyshowproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vakya.bookmyshowproject.dtos.*;
import org.vakya.bookmyshowproject.model.User;
import org.vakya.bookmyshowproject.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;


    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/signup")
    public SignupUserResponseDTO signUp(@RequestBody SignUpUserRequestDTO requestDto){
        SignupUserResponseDTO responseDto = new SignupUserResponseDTO();
        try{
            User user = userService.signUp(
                    requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );
            responseDto.setEmail(user.getEmail());
            responseDto.setName(user.getName());
            responseDto.setUserId(user.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);

        }
        System.out.println("controller");
        System.out.println("controller");
        return responseDto;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto){
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
