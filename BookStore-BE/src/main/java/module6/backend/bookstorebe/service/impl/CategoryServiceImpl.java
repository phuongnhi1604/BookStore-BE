package module6.backend.bookstorebe.service.impl;

import module6.backend.bookstorebe.entity.book.Category;
import module6.backend.bookstorebe.repository.CategoryRepository;
import module6.backend.bookstorebe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
