package com.example.expensetracker.expense_tracker.repository;

import com.example.expensetracker.expense_tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long>
{
    Category findByName(String name);
}
