package io.codescience.repository;

import io.codescience.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS product (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DOUBLE)");
    }

    public int insert(Product product) {
        return jdbcTemplate.update("INSERT INTO product(name, price) VALUES (?, ?)", product.getName(),
                product.getPrice());
    }

    public int[][] batchInsert(List<Product> products) {
        return jdbcTemplate.batchUpdate(
                "INSERT INTO product(name, price) VALUES (?, ?)",
                products,
                products.size(),
                (ps, product) -> {
                    ps.setString(1, product.getName());
                    ps.setDouble(2, product.getPrice());
                });
    }

    public int updatePriceById(long id, double price) {
        return jdbcTemplate.update("UPDATE product SET price = ? WHERE id = ?", price, id);
    }

    public int deleteById(long id) {
        return jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }

    public Product findById(long id) {
        return jdbcTemplate.queryForObject("SELECT id, name, price FROM product WHERE id = ?",
                new BeanPropertyRowMapper<>(Product.class), id);
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT id, name, price FROM product ORDER BY id",
                new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> findByNameLike(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name + "%");
        return namedParameterJdbcTemplate.query(
                "SELECT id, name, price FROM product WHERE name LIKE :name ORDER BY id",
                params,
                BeanPropertyRowMapper.newInstance(Product.class));
    }

    public Map<String, Object> stats() {
        return jdbcTemplate.queryForMap("SELECT COUNT(*) AS cnt, COALESCE(AVG(price), 0) AS avgPrice FROM product");
    }
}
