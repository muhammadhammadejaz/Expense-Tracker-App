package com.example.expensetracker.expense_tracker.controller;

import com.example.expensetracker.expense_tracker.model.Category;
import com.example.expensetracker.expense_tracker.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.URIException;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository)
    {
        super();
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    Collection<Category> categories()
    {
        // select * from category
        return categoryRepository.findAll();
    }

    //Category/2
    @GetMapping("/category/{id}")
    ResponseEntity<?> getCategory(@PathVariable Long id)
    {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/category")
    ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) throws URISyntaxException
    {
        Category result = categoryRepository.save(category);
        return ResponseEntity.created(new URI("/api/category" + result.getId())).body(result);
    }

    @PutMapping("/category/{id}")
    ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category)
    {
        Category result = categoryRepository.save(category);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/category/{id}")
    ResponseEntity<?> deleteCategory(@PathVariable Long id)
    {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
