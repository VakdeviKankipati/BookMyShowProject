package org.vakya.bookmyshowproject.dtos;

import lombok.Getter;
import lombok.Setter;
import org.vakya.bookmyshowproject.model.User;

@Getter
@Setter
public class SignupUserResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
}

