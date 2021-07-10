package net.fincomp.spring.fincomp.service;

import net.fincomp.spring.fincomp.model.Category;
import net.fincomp.spring.fincomp.model.Producer;
import net.fincomp.spring.fincomp.model.Product;
import net.fincomp.spring.fincomp.repository.ProducerRepository;
import net.fincomp.spring.fincomp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }


    public void deleteById(Long product) {
        productRepository.deleteById(product);
    }

    public void saveProduct(Product product, String model, String description, Integer price, Category category, String filename, Producer producer) {
        product.setModel(model);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setProducer(producer);
        if(filename != null) {
            product.setFilename(filename);
        }
        productRepository.save(product);
    }

    public List<Product> findByNameContaining(String filter) {
        return productRepository.findByModelContaining(filter);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findByCategoryAndProducer(Category category, Producer producer) {
        return productRepository.findByCategoryAndProducer(category,producer);
    }

    public Iterable<Product> findByProducer(Producer producer) {
        return productRepository.findByProducer(producer);
    }

    public Iterable<Product> findByCategoryAndProducerAndModelContaining(Category category, Producer producer, String filter) {
        return productRepository.findByCategoryAndProducerAndModelContaining(category,producer,filter);
    }

    public Iterable<Product> findByCategoryAndModelContaining(Category category, String filter) {
        return productRepository.findByCategoryAndModelContaining(category, filter);
    }

    public Iterable<Product> findByProducerAndModelContaining(Producer producer, String filter) {
        return productRepository.findByProducerAndModelContaining(producer, filter);
    }
}
