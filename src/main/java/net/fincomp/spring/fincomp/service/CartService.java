package net.fincomp.spring.fincomp.service;

import net.fincomp.spring.fincomp.model.Cart;
import net.fincomp.spring.fincomp.model.Product;
import net.fincomp.spring.fincomp.model.User;
import net.fincomp.spring.fincomp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void saveCart(Cart cart, Product product, User user, Integer count) {
        cart.setProduct(product);
        cart.setUser(user);
        cart.setCount(count);

        cartRepository.save(cart);
    }

    public void saveCart(Cart cart, Product product, String nonUser, Integer count) {
        cart.setProduct(product);
        cart.setNonUser(nonUser);
        cart.setCount(count);

        cartRepository.save(cart);
    }

    public Iterable<Cart> findByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public void deleteById(Long cart) {
        cartRepository.deleteById(cart);
    }


    public Iterable<Cart> findByNonUser(String nonUser) {
        return cartRepository.findByNonUser(nonUser);
    }

    public Cart findByProductAndUser(Product product, User user) {
        return cartRepository.findByProductAndUser(product, user);
    }

    public Cart findByProductAndNonUser(Product product, String nonUser) {
        return cartRepository.findByProductAndNonUser(product, nonUser);
    }
}
