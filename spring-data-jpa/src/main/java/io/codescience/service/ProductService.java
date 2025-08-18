package io.codescience.service;

import io.codescience.model.Product;
import io.codescience.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 创建单个产品
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * 批量创建产品
     */
    public List<Product> batchCreateProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    /**
     * 更新产品价格
     */
    public boolean updateProductPrice(Long id, Double price) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setPrice(price);
                    productRepository.save(product);
                    return true;
                })
                .orElse(false);
    }

    /**
     * 删除产品
     */
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * 根据ID查询产品
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * 查询所有产品
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * 根据名称模糊查询
     */
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingOrderById(name);
    }

    /**
     * 获取产品统计信息
     */
    public Map<String, Object> getProductStats() {
        Object[] stats = productRepository.getStats();
        Map<String, Object> result = new HashMap<>();
        result.put("cnt", stats[0]);
        result.put("avgPrice", stats[1]);
        return result;
    }
}
