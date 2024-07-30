package org.vakya.bookmyshowproject.dtos;

import lombok.Getter;
import lombok.Setter;
import org.vakya.bookmyshowproject.model.User;

@Getter
@Setter
public class SignupUserResponseDTO {
    private ResponseStatus responseStatus;
    private String name;
    private String email;
    private long userId;

}

