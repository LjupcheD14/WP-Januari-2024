package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public List<Category> listAll() {
        return null;
    }

    @Override
    public Category create(String name) {
        return null;
    }
}
