package de.szut.webshop.supplier.service;

import de.szut.webshop.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }
}
