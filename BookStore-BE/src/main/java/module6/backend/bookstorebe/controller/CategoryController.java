package module6.backend.bookstorebe.controller;

import module6.backend.bookstorebe.entity.book.Book;
import module6.backend.bookstorebe.entity.book.Category;
import module6.backend.bookstorebe.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        try{
            Category category = categoryService.findById(id);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categoryList = categoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }
}
