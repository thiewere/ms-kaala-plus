package com.thifuge.kaala_plus.suppliers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public void createSupplier(Supplier supplier) {
        log.info("Creating new supplier {}", supplier);
        this.supplierRepository.save(supplier);
    }

    public List<Supplier> findAllSuppliers() {
        log.info("Finding all suppliers");
        return this.supplierRepository.findAll();
    }

    public Supplier findSupplier(Integer id) {
        log.info("Finding supplier with id {}", id);
        Optional<Supplier> supplier = this.supplierRepository.findById(id);
        return supplier.orElseThrow(() -> new EntityNotFoundException(
                "Supplier with id " + id + " not found."
        ));
    }

    public Supplier findSupplierByName(String name) {
        log.info("Finding supplier by name {}", name);
        Optional<Supplier> supplier = this.supplierRepository.findByName(name);
        return supplier.orElseThrow(() -> new EntityNotFoundException(
                "Supplier by name " + name + " not found."
        ));
    }

    public Supplier findSupplierByPhone(String phone) {
        log.info("Finding supplier by phone {}", phone);
        Optional<Supplier> supplier = this.supplierRepository.findByPhone(phone);
        return supplier.orElse(null);
    }

    public Supplier updateSupplier(Integer id, Supplier supplier) {
        Supplier supplierInDB = this.findSupplier(id);

        log.info("Updating supplier with id {}", id);
        supplierInDB.setName(supplier.getName());
        supplierInDB.setPhone(supplier.getPhone());
        supplierInDB.setAddress(supplier.getAddress());
        supplierInDB.setUpdatedAt(supplier.getUpdatedAt());

        supplierInDB = this.supplierRepository.save(supplierInDB);
        return supplierInDB;
    }

    public void deleteSupplier(Integer id) {
        Supplier supplier = this.findSupplier(id);
        log.info("Deleting supplier with id {}", id);
        this.supplierRepository.delete(supplier);
    }
}
