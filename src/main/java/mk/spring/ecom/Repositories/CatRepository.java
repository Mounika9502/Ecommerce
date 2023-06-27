package mk.spring.ecom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mk.spring.ecom.Categories;

public interface CatRepository extends JpaRepository<Categories,Long> {

}
