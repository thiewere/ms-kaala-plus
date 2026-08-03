package com.thifuge.kaala_plus.products;

import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(ApiEndpoints.PRODUCTS)
public class ProductController {

    private ProductService productService;

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return this.productService.createProduct(product);
    }

    @GetMapping()
    public List<Product> findAllProducts() {
        return this.productService.findAllProducts();
    }

    @GetMapping(path = "{id}")
    public Product findProduct(@PathVariable int id) {
        return this.productService.findProduct(id);
    }

    @PutMapping(path = "{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
        return this.productService.updateProduct(id, product);
    }

    @DeleteMapping(path = "{id}")
    public void deleteProduct(@PathVariable int id) {
        this.productService.deleteProduct(id);
    }
}
