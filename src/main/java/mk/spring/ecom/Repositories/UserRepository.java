package mk.spring.ecom.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mk.spring.ecom.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
  Optional<Users> findByEmail(String email); 
}
