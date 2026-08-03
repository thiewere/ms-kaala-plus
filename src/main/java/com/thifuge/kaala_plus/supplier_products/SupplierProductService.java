package com.thifuge.kaala_plus.supplier_products;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

    public void createSupplierProduct(SupplierProduct supplierProduct) {
        log.info("Creating a new supplier_product: {}", supplierProduct);
        this.supplierProductRepository.save(supplierProduct);
    }

    public List<SupplierProduct> findAllSupplierProducts() {
        log.info("Getting all supplier_products");
        return this.supplierProductRepository.findAll();
    }

    public SupplierProduct findSupplierProduct(int id) {
        log.info("Finding supplier_product by id: {}", id);
        Optional<SupplierProduct> optionalSupplierProduct = this.supplierProductRepository.findById(id);
        return optionalSupplierProduct.orElseThrow(() -> new EntityNotFoundException(
                "Supplier product with id: " + id + " not found."
        ));
    }

    public SupplierProduct updateSupplierProduct(int id, SupplierProduct supplierProduct) {
        SupplierProduct supplierProductInDB = this.findSupplierProduct(id);

        log.info("Updating supplier_product by id: {}", id);
        supplierProductInDB.setSupplier(supplierProduct.getSupplier());
        supplierProductInDB.setProduct(supplierProduct.getProduct());
        supplierProductInDB.setUnitPrice(supplierProduct.getUnitPrice());
        supplierProductInDB =  this.supplierProductRepository.save(supplierProductInDB);
        return supplierProductInDB;
    }

    public void deleteSupplierProduct(int id) {
        SupplierProduct supplierProductInDB = this.findSupplierProduct(id);
        log.info("Deleting supplier_product by id: {}", id);
        this.supplierProductRepository.delete(supplierProductInDB);
    }
}
