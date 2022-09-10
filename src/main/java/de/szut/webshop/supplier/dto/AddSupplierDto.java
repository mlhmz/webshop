package de.szut.webshop.supplier.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AddSupplierDto {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Street is mandatory")
    @Size(max = 50, message = "Street cannot exceed 50 characters")
    private String street;

    @NotBlank(message = "Postcode is mandatory")
    @Size(min = 5, max = 5, message = "Postcode must be exactly 5 characters")
    private String postcode;

    @NotBlank(message = "City is mandatory")
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    private String phone;
}
