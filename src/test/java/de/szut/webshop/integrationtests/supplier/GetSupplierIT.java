package de.szut.webshop.integrationtests.supplier;

import de.szut.webshop.contact.entity.Contact;
import de.szut.webshop.supplier.entity.Supplier;
import de.szut.webshop.testcontainers.AbstractIntegrationTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetSupplierIT extends AbstractIntegrationTest {
    @Test
    public void findAll() throws Exception {

        Supplier supplier = createSupplier();

        this.supplierRepository.save(supplier);

        final var contentAsString = this.mockMvc.perform(get("/api/v1/supplier"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Mustermann")))
                .andExpect(jsonPath("$[0].street", is("Musterstr. 1")))
                .andExpect(jsonPath("$[0].postcode", is("12345")))
                .andExpect(jsonPath("$[0].city", is("Musterstadt")))
                .andExpect(jsonPath("$[0].phone", is("+4912345")));
    }

    @Test
    public void findCertainSupplier() throws Exception {
        Supplier supplier = createSupplier();

        this.supplierRepository.save(supplier);

        final var contentAsString = this.mockMvc.perform(get("/api/v1/supplier/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("name", is("Mustermann")))
                .andExpect(jsonPath("street", is("Musterstr. 1")))
                .andExpect(jsonPath("postcode", is("12345")))
                .andExpect(jsonPath("city", is("Musterstadt")))
                .andExpect(jsonPath("phone", is("+4912345")));
    }

    @NotNull
    private static Supplier createSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("Mustermann");
        Contact contact = new Contact();
        contact.setStreet("Musterstr. 1");
        contact.setPostcode("12345");
        contact.setCity("Musterstadt");
        contact.setPhone("+4912345");
        supplier.setContact(contact);
        return supplier;
    }

}
