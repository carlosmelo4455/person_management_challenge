package com.carlos.person_management_app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCreateDTO {
    @NotBlank(message = "nome")
    private String fullName;
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "data")
    private String birthDate;
    private AddressCreateDTO address;
}
