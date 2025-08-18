package io.codescience.controller;

import io.codescience.model.Product;
import io.codescience.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.productRepository.createTable();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Product product) {
        productRepository.insert(product);
        return ResponseEntity.created(URI.create("/api/products")).build();
    }

    @PostMapping("/batch")
    public int[][] batch(@RequestBody List<Product> products) {
        return productRepository.batchInsert(products);
    }

    @PutMapping("/{id}/price/{price}")
    public int updatePrice(@PathVariable long id, @PathVariable double price) {
        return productRepository.updatePriceById(id, price);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable long id) {
        return productRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable long id) {
        return productRepository.findById(id);
    }

    @GetMapping
    public List<Product> list(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isBlank()) {
            return productRepository.findAll();
        }
        return productRepository.findByNameLike(name);
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        return productRepository.stats();
    }
}
