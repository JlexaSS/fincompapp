package net.fincomp.spring.fincomp.repository;

import net.fincomp.spring.fincomp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findAll();

    List<Category> findByNameContaining(String name);
}
