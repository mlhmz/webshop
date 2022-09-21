package de.szut.webshop.integrationtests.supplier;

import de.szut.webshop.contact.entity.Contact;
import de.szut.webshop.supplier.entity.Supplier;
import de.szut.webshop.testcontainers.AbstractIntegrationTest;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostSupplierIT extends AbstractIntegrationTest {

    public static final String JSON_NAME_PATH = "name";
    public static final String JSON_STREET_PATH = "street";
    public static final String JSON_CITY_PATH = "city";
    public static final String JSON_POST_CODE_PATH = "postcode";
    public static final String JSON_PHONE_PATH = "phone";

    private final String name = "Mustermann";
    private final String street = "Musterstraße 12";
    private final String postcode = "12345";
    private final String city = "Musterstadt";
    private final String phone = "012345678";

    @Test
    public void postSupplier() throws Exception {
        String content = fillJSONContent();
        String contentAsString =
                checkContentWithMockMvc(content);
        testSupplier(contentAsString);
    }

    @NotNull
    private String checkContentWithMockMvc(String content) throws Exception {
        return mockMvc.perform(
                        post("/api/v1/supplier").content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath(JSON_NAME_PATH, is(name)))
                .andExpect(jsonPath(JSON_STREET_PATH, is(street)))
                .andExpect(jsonPath(JSON_CITY_PATH, is(city)))
                .andExpect(jsonPath(JSON_POST_CODE_PATH, is(postcode)))
                .andExpect(jsonPath(JSON_PHONE_PATH, is(phone)))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String fillJSONContent() {
        return String.format("""
                {
                    "%s": "%s",
                    "%s": "%s",
                    "%s": "%s",
                    "%s": "%s",
                    "%s": "%s"
                }
                """,
                JSON_NAME_PATH, name,
                JSON_STREET_PATH, street,
                JSON_POST_CODE_PATH, postcode,
                JSON_CITY_PATH, city,
                JSON_PHONE_PATH, phone
        );
    }

    private void testSupplier(String contentAsString) throws JSONException {
        long id = Long.parseLong(new JSONObject(contentAsString).get("id").toString());

        Optional<Supplier> optSupplier = supplierRepository.findById(id);

        assertTrue(optSupplier.isPresent(), "Supplier must be present");

        Supplier supplier = optSupplier.get();

        assertEquals(supplier.getName(), name);

        Contact contact = supplier.getContact();

        testContact(street, postcode, city, phone, contact);
    }

    private void testContact(String street, String postcode, String city, String phone, Contact contact) {
        assertEquals(contact.getStreet(), street);
        assertEquals(contact.getPostcode(), postcode);
        assertEquals(contact.getCity(), city);
        assertEquals(contact.getStreet(), street);
        assertEquals(contact.getPhone(), phone);
    }
}
