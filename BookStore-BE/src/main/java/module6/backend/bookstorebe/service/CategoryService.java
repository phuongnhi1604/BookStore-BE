package module6.backend.bookstorebe.service;

import module6.backend.bookstorebe.entity.book.Category;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);
    List<Category> findAllCategory();
}
