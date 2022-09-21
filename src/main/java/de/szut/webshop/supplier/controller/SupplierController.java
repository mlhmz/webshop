package de.szut.webshop.supplier.controller;

import de.szut.webshop.exception.ResourceNotFoundException;
import de.szut.webshop.service.MappingService;
import de.szut.webshop.supplier.dto.AddSupplierDto;
import de.szut.webshop.supplier.dto.GetSupplierDto;
import de.szut.webshop.supplier.entity.Supplier;
import de.szut.webshop.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    private final SupplierService service;
    private final MappingService mapper;

    @Autowired
    public SupplierController(SupplierService service, MappingService mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<GetSupplierDto> createSupplier(@Valid @RequestBody AddSupplierDto dto) {
        Supplier supplier = mapper.mapAddSupplierDtoToSupplier(dto);
        supplier = service.create(supplier);
        final GetSupplierDto request = mapper.mapSupplierToGetSupplierDto(supplier);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetSupplierDto>> findAllSuppliers() {
        return new ResponseEntity<>(service.readAll().stream()
                .map(mapper::mapSupplierToGetSupplierDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSupplierDto> getSupplierById(@PathVariable long id) throws ResourceNotFoundException {
        Supplier supplier = service.readById(id);
        GetSupplierDto dto = mapper.mapSupplierToGetSupplierDto(supplier);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GetSupplierDto> updateSupplier(@RequestBody Supplier supplier) throws ResourceNotFoundException {
        supplier = service.update(supplier);
        GetSupplierDto dto = mapper.mapSupplierToGetSupplierDto(supplier);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplierById(@PathVariable long id) {
        service.deleteById(id);
    }


}
