package de.szut.webshop.supplier.service;

import de.szut.webshop.exception.ResourceNotFoundException;
import de.szut.webshop.supplier.entity.Supplier;
import de.szut.webshop.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for crud operations of the supplier
 */
@Service
public class SupplierService {
    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a {@link Supplier}
     *
     * @param supplier Supplier to create
     * @return the created supplier
     */
    public Supplier create(Supplier supplier) {
        return this.repository.save(supplier);
    }

    /**
     * Gets all suppliers
     *
     * @return List of suppliers
     */
    public List<Supplier> readAll() {
        return this.repository.findAll();
    }

    /**
     * Gets specific {@link Supplier}
     *
     * @param id the id of the searched supplier
     * @return the specific supplier
     * @throws ResourceNotFoundException when there was no supplier found
     */
    public Supplier readById(long id) throws ResourceNotFoundException {
        Optional<Supplier> supplier = this.repository.findById(id);

        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException(Supplier.class, id);
        }

        return supplier.get();
    }

    /**
     * Updates the {@link Supplier}
     * The executor is required to pass a Supplier which is in a transaction
     * and already on the database
     *
     * @param supplier The supplier to update
     * @return The updated supplier
     * @throws ResourceNotFoundException When no supplier was found on the db with the certain id
     */
    public Supplier update(Supplier supplier) throws ResourceNotFoundException {
        if (!this.repository.existsById(supplier.getId())) {
            throw new ResourceNotFoundException(Supplier.class, supplier.getId());
        }
        return this.repository.save(supplier);
    }

    /**
     * Deletes the Supplier of a certain id without callback
     *
     * @param id suppliers id that will be deleted
     */
    public void deleteById(long id) {
        this.repository.deleteById(id);
    }
}
