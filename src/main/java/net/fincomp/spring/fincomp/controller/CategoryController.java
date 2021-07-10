package net.fincomp.spring.fincomp.controller;

import net.fincomp.spring.fincomp.model.Category;
import net.fincomp.spring.fincomp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/category")
    public String add(Category category, Model model){
        categoryService.saveCategory(category);
        model.addAttribute("categories", categoryService.findAll());
        return "categoryList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("admin/category/edit")
    public String saveCategory(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam("categoryId") Category category
    ){
        categoryService.saveCategory(category, name, description);

        return "redirect:/admin/category";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/category")
    public String findCategory(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Category> categories = categoryService.findAll();

        if (filter != null && !filter.isEmpty()) {
            categories = categoryService.findByNameContaining(filter);
        } else {
            categories = categoryService.findAll();
        }

        model.addAttribute("categories", categories);
        model.addAttribute("filter", filter);

        return "categoryList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/category/delete/{category}")
    public String deleteUser(@PathVariable("category") Long category){
        categoryService.deleteById(category);

        return "redirect:/admin/category";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/category/edit/{category}")
    public String userEditForm(@PathVariable Category category, Model model){
        model.addAttribute("categories", category);
        return "categoryEdit";
    }
}
