package de.szut.webshop.integrationtests.supplier;

import de.szut.webshop.contact.entity.Contact;
import de.szut.webshop.supplier.entity.Supplier;
import de.szut.webshop.testcontainers.AbstractIntegrationTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetSupplierIT extends AbstractIntegrationTest {
    private final String name = "Mustermann";
    private final String street = "Musterstrasse 12";
    private final String postcode = "12345";
    private final String city = "Musterstadt";
    private final String phone = "012345678";

    @Test
    public void findAll() throws Exception {
        Supplier supplier = createSupplier();

        this.supplierRepository.save(supplier);

        mockMvc.perform(get("/api/v1/supplier"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(name)))
                .andExpect(jsonPath("$[0].street", is(street)))
                .andExpect(jsonPath("$[0].postcode", is(postcode)))
                .andExpect(jsonPath("$[0].city", is(city)))
                .andExpect(jsonPath("$[0].phone", is(phone)));
    }

    @Test
    public void findCertainSupplier() throws Exception {
        Supplier supplier = createSupplier();

        this.supplierRepository.save(supplier);

        mockMvc.perform(
                get("/api/v1/supplier/1")
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("name", is(name)))
                .andExpect(jsonPath("street", is(street)))
                .andExpect(jsonPath("postcode", is(postcode)))
                .andExpect(jsonPath("city", is(city)))
                .andExpect(jsonPath("phone", is(phone)));
    }

    @NotNull
    private Supplier createSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName(name);
        Contact contact = new Contact();
        contact.setStreet(street);
        contact.setPostcode(postcode);
        contact.setCity(city);
        contact.setPhone(phone);
        supplier.setContact(contact);
        return supplier;
    }
}
