package com.thifuge.kaala_plus.suppliers;

import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.SUPPLIERS)
public class SupplierController {

    private SupplierService supplierService;

    @PostMapping
    public void create(@RequestBody Supplier supplier) {
        this.supplierService.createSupplier(supplier);
    }

    @GetMapping
    public List<Supplier> findAll() {
        return this.supplierService.findAllSuppliers();
    }

    @GetMapping(path = "{id}")
    public Supplier findSupplier(@PathVariable int id) {
        return this.supplierService.findSupplier(id);
    }

    @PutMapping(path = "{id}")
    public Supplier updateSupplier(@PathVariable int id, @RequestBody Supplier supplier) {
        return this.supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSupplier(@PathVariable int id) {
        this.supplierService.deleteSupplier(id);
    }
}
