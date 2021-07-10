package net.fincomp.spring.fincomp.service;

import net.fincomp.spring.fincomp.model.Category;
import net.fincomp.spring.fincomp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }


    public void deleteById(Long category) {
        categoryRepository.deleteById(category);
    }

    public void saveCategory(Category category, String name, String description) {
        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
    }

    public List<Category> findByNameContaining(String filter) {
        return categoryRepository.findByNameContaining(filter);
    }
}
