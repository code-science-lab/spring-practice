package io.codescience.repository;

import io.codescience.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * 根据名称模糊查询
     */
    List<Product> findByNameContainingOrderById(String name);

    /**
     * 自定义查询：获取产品统计信息
     */
    @Query("SELECT COUNT(p) as cnt, COALESCE(AVG(p.price), 0) as avgPrice FROM Product p")
    Object[] getStats();

    /**
     * 批量保存产品
     */
    <S extends Product> List<S> saveAll(Iterable<S> entities);
}
