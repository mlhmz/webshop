package de.szut.webshop.contact;

import de.szut.webshop.supplier.Supplier;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Table(name = "contact")
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(
            mappedBy = "contact",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private Supplier supplier;

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
