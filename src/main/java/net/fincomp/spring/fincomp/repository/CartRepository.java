package net.fincomp.spring.fincomp.repository;

import net.fincomp.spring.fincomp.model.Cart;
import net.fincomp.spring.fincomp.model.Product;
import net.fincomp.spring.fincomp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart,Long> {
    List<Cart> findAll();
    List<Cart> findByUser(User user);
    List<Cart> findByNonUser(String nonUser);
    Cart findByProductAndUser(Product product, User user);
    Cart findByProductAndNonUser(Product product, String nonUser);
}
