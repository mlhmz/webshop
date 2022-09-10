package de.szut.webshop.supplier.dto;

import lombok.Data;

@Data
public class GetSupplierDto {
    private Long id;
    private String name;
    private String street;
    private String postcode;
    private String city;
    private String phone;
}
