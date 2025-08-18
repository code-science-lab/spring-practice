package io.codescience.controller;

import io.codescience.model.Product;
import io.codescience.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 创建单个产品
     */
    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.created(URI.create("/api/products/" + savedProduct.getId())).build();
    }

    /**
     * 批量创建产品
     */
    @PostMapping("/batch")
    public ResponseEntity<List<Product>> batchCreateProducts(@RequestBody List<Product> products) {
        List<Product> savedProducts = productService.batchCreateProducts(products);
        return ResponseEntity.ok(savedProducts);
    }

    /**
     * 更新产品价格
     */
    @PutMapping("/{id}/price/{price}")
    public ResponseEntity<String> updateProductPrice(@PathVariable Long id, @PathVariable Double price) {
        boolean updated = productService.updateProductPrice(id, price);
        if (updated) {
            return ResponseEntity.ok("Product price updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 删除产品
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 根据ID查询产品
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 查询所有产品或根据名称模糊查询
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam(value = "name", required = false) String name) {
        List<Product> products;
        if (name != null && !name.trim().isEmpty()) {
            products = productService.getProductsByName(name);
        } else {
            products = productService.getAllProducts();
        }
        return ResponseEntity.ok(products);
    }

    /**
     * 获取产品统计信息
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getProductStats() {
        Map<String, Object> stats = productService.getProductStats();
        return ResponseEntity.ok(stats);
    }
}
