package mk.spring.ecom.Repositories;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mk.spring.ecom.Review;

 

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
    

}