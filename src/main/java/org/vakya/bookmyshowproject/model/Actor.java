package org.vakya.bookmyshowproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Getter
@Setter
@Entity
public class Actor extends BaseModel{
    private String name;

}
