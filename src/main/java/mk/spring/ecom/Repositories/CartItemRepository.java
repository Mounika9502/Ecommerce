package mk.spring.ecom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.spring.ecom.CartItem;

 

 

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}