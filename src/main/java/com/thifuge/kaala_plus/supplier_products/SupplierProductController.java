package com.thifuge.kaala_plus.supplier_products;

import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.SUPPLIER_PRODUCTS)
public class SupplierProductController {

    private final SupplierProductService supplierProductService;

    @PostMapping()
    public void createSupplierProduct(@RequestBody SupplierProduct supplierProduct) {
        this.supplierProductService.createSupplierProduct(supplierProduct);
    }

    @GetMapping()
    public List<SupplierProduct> findAllSupplierProducts() {
        return this.supplierProductService.findAllSupplierProducts();
    }

    @GetMapping(path = "{id}")
    public SupplierProduct findSupplierProductById(@PathVariable int id) {
        return this.supplierProductService.findSupplierProduct(id);
    }

    @PutMapping(path = "{id}")
    public SupplierProduct updateSupplierProduct(@PathVariable int id, @RequestBody SupplierProduct supplierProduct) {
        return this.supplierProductService.updateSupplierProduct(id, supplierProduct);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSupplierProduct(@PathVariable int id) {
        this.supplierProductService.deleteSupplierProduct(id);
    }
}
