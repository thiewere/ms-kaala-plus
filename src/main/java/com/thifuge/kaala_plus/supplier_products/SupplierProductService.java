package com.thifuge.kaala_plus.supplier_products;

import com.thifuge.kaala_plus.products.Product;
import com.thifuge.kaala_plus.products.ProductRepository;
import com.thifuge.kaala_plus.products.ProductService;
import com.thifuge.kaala_plus.supplier_products.dto.CreateProductWithSupplierRequest;
import com.thifuge.kaala_plus.supplier_products.dto.CreateSupplierProductRequest;
import com.thifuge.kaala_plus.supplier_products.dto.CreateSupplierProductResponse;
import com.thifuge.kaala_plus.suppliers.Supplier;
import com.thifuge.kaala_plus.suppliers.SupplierRepository;
import com.thifuge.kaala_plus.suppliers.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierService supplierService;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Transactional
    public CreateSupplierProductResponse createSupplierProduct(CreateSupplierProductRequest request) {
        if (this.supplierProductRepository
                .existsBySupplierIdAndProductId(request.supplierId(), request.productId())
        ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This Supplier is already associated with this product");
        }

        Product product = this.productService.findProduct(request.productId());

        Supplier supplier = this.supplierService.findSupplier(request.supplierId());

        SupplierProduct supplierProduct = new SupplierProduct();
        supplierProduct.setSupplier(supplier);
        supplierProduct.setProduct(product);
        supplierProduct.setUnitPrice(request.unitPrice());

        supplierProduct = this.supplierProductRepository.save(supplierProduct);

        return new CreateSupplierProductResponse(
                supplierProduct.getId(),
                supplierProduct.getProduct().getId(),
                supplierProduct.getSupplier().getId(),
                supplierProduct.getUnitPrice(),
                supplierProduct.getCurrency().getName()
        );
    }

    @Transactional
    public CreateSupplierProductResponse createProductWithSupplier(CreateProductWithSupplierRequest request) {
        if(this.productRepository.existsByNameIgnoreCase(request.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "A product with this name already exists"
            );
        }
        Product product = new Product();
        product.setName(request.name());
        product.setBrand(request.brand());
        product.setDescription(request.description());

        product = this.productRepository.save(product);

        Supplier supplier = this.supplierService.findSupplier(request.supplierId());

        SupplierProduct supplierProduct = new SupplierProduct();
        supplierProduct.setSupplier(supplier);
        supplierProduct.setProduct(product);
        supplierProduct.setUnitPrice(request.unitPrice());

        supplierProduct = this.supplierProductRepository.save(supplierProduct);

        return new CreateSupplierProductResponse(
                supplierProduct.getId(),
                supplierProduct.getProduct().getId(),
                supplierProduct.getSupplier().getId(),
                supplierProduct.getUnitPrice(),
                supplierProduct.getCurrency().getName()
        );
    }

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
