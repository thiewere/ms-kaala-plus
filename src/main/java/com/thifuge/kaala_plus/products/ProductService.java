package com.thifuge.kaala_plus.products;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        log.info("Creating new product: {}", product);
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        log.info("Finding all products");
        return productRepository.findAll();
    }

    public Product findProduct(int id) {
        log.info("Finding product by id: {}", id);
        Optional<Product> optionalProduct = this.productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

   public Product updateProduct(int id, Product product) {
        log.info("Updating product by id: {}", id);
        Product productInDB = this.findProduct(id);
        productInDB.setName(product.getName());
        productInDB = this.productRepository.save(productInDB);
        return productInDB;
   }

   public void deleteProduct(int id) {
        Product productInDB = this.findProduct(id);
        log.info("Deleting product mit id: {}", id);
        this.productRepository.delete(productInDB);
   }
}
