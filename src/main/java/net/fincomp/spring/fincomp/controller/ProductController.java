package net.fincomp.spring.fincomp.controller;

import net.fincomp.spring.fincomp.model.Category;
import net.fincomp.spring.fincomp.model.Producer;
import net.fincomp.spring.fincomp.model.Product;
import net.fincomp.spring.fincomp.service.CategoryService;
import net.fincomp.spring.fincomp.service.ProducerService;
import net.fincomp.spring.fincomp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ProductController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProducerService producerService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/product")
    public String addProduct(Product product, Model model, @RequestParam("file") MultipartFile file) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String filename = saveFile(file);
            product.setFilename(filename);
        }


        productService.saveProduct(product);
        Iterable<Category> categories = categoryService.findAll();
        Iterable<Producer> producers = producerService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("producers", producers);
        model.addAttribute("products", productService.findAll());
        return "productsList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("admin/product/edit")
    public String saveProduct(
            @RequestParam String model,
            @RequestParam String description,
            @RequestParam Integer price,
            @RequestParam Category category,
            @RequestParam Producer producer,
            @RequestParam("file") MultipartFile file,
            @RequestParam("productId") Product product
    ) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String filename = saveFile(file);
            productService.saveProduct(product, model, description, price, category, filename, producer);
        } else{
            productService.saveProduct(product, model, description, price, category, null, producer);
        }


        return "redirect:/admin/product";
    }

    private String saveFile(@RequestParam("file") MultipartFile file) throws IOException {
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resultFilename));
        return resultFilename;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/product")
    public String findProduct(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Product> products = productService.findAll();
        Iterable<Category> categories = categoryService.findAll();
        Iterable<Producer> producers = producerService.findAll();

        if (filter != null && !filter.isEmpty()) {
            products = productService.findByNameContaining(filter);
        } else {
            products = productService.findAll();
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("producers", producers);
        model.addAttribute("filter", filter);

        return "productsList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/product/delete/{product}")
    public String deleteProduct(@PathVariable("product") Long product){
        productService.deleteById(product);

        return "redirect:/admin/product";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/product/edit/{product}")
    public String productEditForm(@PathVariable Product product, Model model){
        Iterable<Category> categories = categoryService.findAll();
        Iterable<Producer> producers = producerService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("producers", producers);
        model.addAttribute("products", product);
        return "productEdit";
    }
}
