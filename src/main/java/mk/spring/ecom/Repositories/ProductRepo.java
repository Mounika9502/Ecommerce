package mk.spring.ecom.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mk.spring.ecom.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM Products WHERE catid = :catidd", nativeQuery = true)
    List<Product> findByCatid(@Param("catidd") Long catidd);
}