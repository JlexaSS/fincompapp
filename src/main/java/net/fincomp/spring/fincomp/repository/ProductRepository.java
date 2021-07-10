package net.fincomp.spring.fincomp.repository;

import net.fincomp.spring.fincomp.model.Category;
import net.fincomp.spring.fincomp.model.Producer;
import net.fincomp.spring.fincomp.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findByModelContaining(String model);
    List<Product> findAll();
    List<Product> findByCategory(Category category);
    List<Product> findByCategoryAndProducer(Category category, Producer producer);
    List<Product> findByProducer(Producer producer);
    List<Product> findByCategoryAndProducerAndModelContaining(Category category, Producer producer, String filter);
    List<Product> findByCategoryAndModelContaining(Category category, String filter);
    List<Product> findByProducerAndModelContaining(Producer producer, String filter);
}
