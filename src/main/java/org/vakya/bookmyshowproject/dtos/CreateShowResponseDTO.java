package org.vakya.bookmyshowproject.dtos;

import lombok.Data;
import org.vakya.bookmyshowproject.model.Show;

@Data
public class CreateShowResponseDTO {
    private ResponseStatus responseStatus;
    private Show show;
}
