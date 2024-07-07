package org.vakya.bookmyshowproject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserRequestDTO {
    private String name;
    private String email;
    private String password;
}
