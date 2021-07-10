package net.fincomp.spring.fincomp.controller;

import net.fincomp.spring.fincomp.model.*;
import net.fincomp.spring.fincomp.repository.IpRepository;
import net.fincomp.spring.fincomp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private IpRepository ipRepository;

    @GetMapping("/")
    public String main(Map<String, Object> model) {
        return "main";
    }

    @GetMapping("/catalog")
    public String findProduct(@RequestParam(required = false, defaultValue = "") String filter,
                              @RequestParam(required = false, defaultValue = "") Category category_filter,
                              @RequestParam(required = false, defaultValue = "") Producer producer_filter,
                              Model model) {
        Iterable<Product> products = productService.findAll();


        if(filter.isEmpty() && category_filter == null && producer_filter == null){
            products = productService.findAll();
        } else if(category_filter != null && producer_filter != null && filter.isEmpty()){
            products = productService.findByCategoryAndProducer(category_filter, producer_filter);
        } else if(producer_filter == null && category_filter != null && filter.isEmpty()){
            products = productService.findByCategory(category_filter);
        } else if(producer_filter != null && category_filter == null && filter.isEmpty()){
            products = productService.findByProducer(producer_filter);
        } else if(producer_filter != null && category_filter != null && !filter.isEmpty()){
            products = productService.findByCategoryAndProducerAndModelContaining(category_filter,producer_filter, filter);
        } else if(producer_filter == null && category_filter != null && !filter.isEmpty()){
            products = productService.findByCategoryAndModelContaining(category_filter, filter);
        } else if(producer_filter != null && category_filter == null && !filter.isEmpty()){
            products = productService.findByProducerAndModelContaining(producer_filter, filter);
        } else if(producer_filter == null && category_filter == null && !filter.isEmpty()){
            products = productService.findByNameContaining(filter);
        }

        Iterable<Category> categories = categoryService.findAll();
        Iterable<Producer> producers = producerService.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("producers", producers);
        model.addAttribute("filter", filter);
        model.addAttribute("category_filter", category_filter);
        model.addAttribute("producer_filter", producer_filter);
        return "catalog";
    }

    @GetMapping("/cart")
    public String cart(@AuthenticationPrincipal User user,
                       HttpServletRequest request,
                       Model model) {
        String nonUser = ipRepository.getClientIp(request);
        if (user != null) {
            Iterable<Cart> cart = cartService.findByUser(user);
            model.addAttribute("carts", cart);
        } else {
            Iterable<Cart> cart = cartService.findByNonUser(nonUser);
            model.addAttribute("carts", cart);
        }
        model.addAttribute("price", 0);
        return "cart";
    }

    @PostMapping("/catalog")
    public String addInCart(
            @AuthenticationPrincipal User user,
            @RequestParam Product product,
            @RequestParam Integer count,
            HttpServletRequest request,
            Cart cart,
            Model model){
        String nonUser = ipRepository.getClientIp(request);
        if (user != null) {
            Cart carts = cartService.findByProductAndUser(product, user);
            if(carts != null) {
                carts.IncCount(count);
                cartService.saveCart(carts);
            } else {
                cartService.saveCart(cart, product, user, count);
            }
        } else {
            Cart carts = cartService.findByProductAndNonUser(product, nonUser);
            if(carts != null) {
                carts.IncCount(count);
                cartService.saveCart(carts);
            } else {
                cartService.saveCart(cart, product, nonUser, count);
            }
        }
        model.addAttribute("cart", "Товар добавлен!");

        Iterable<Category> categories = categoryService.findAll();
        Iterable<Producer> producers = producerService.findAll();
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("producers", producers);

        return "catalog";
    }

    @GetMapping("/cart/{cart}")
    public String deleteCart(@PathVariable("cart") Long cart){
        cartService.deleteById(cart);

        return "redirect:/cart";
    }

    @PostMapping("/cart")
    public String updateCart(@RequestParam Integer count, @RequestParam Cart cart, Model model){
        cart.setCount(count);
        cartService.saveCart(cart);
        return "redirect:/cart";
    }
}
