package de.szut.webshop.service;

import de.szut.webshop.contact.entity.Contact;
import de.szut.webshop.supplier.dto.AddSupplierDto;
import de.szut.webshop.supplier.dto.GetSupplierDto;
import de.szut.webshop.supplier.entity.Supplier;
import org.springframework.stereotype.Service;

@Service
public class MappingService {
    public Supplier mapAddSupplierDtoToSupplier(AddSupplierDto dto) {
        Supplier supplier = new Supplier();

        Contact contact = new Contact();
        contact.setStreet(dto.getStreet());
        contact.setPostcode(dto.getPostcode());
        contact.setCity(dto.getCity());
        contact.setPhone(dto.getPhone());

        supplier.setName(dto.getName());
        supplier.setContact(contact);
        contact.setSupplier(supplier);

        return supplier;
    }

    public GetSupplierDto mapSupplierToGetSupplierDto(Supplier supplier) {
        GetSupplierDto dto = new GetSupplierDto();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());

        Contact contact = supplier.getContact();
        dto.setStreet(contact.getStreet());
        dto.setPostcode(contact.getPostcode());
        dto.setCity(contact.getCity());
        dto.setPhone(contact.getPhone());

        return dto;
    }
}
