package mk.spring.ecom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.spring.ecom.Order;

 



 

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}