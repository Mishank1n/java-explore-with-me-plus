package ru.practicum.category.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.category.model.Category;
import ru.practicum.category.model.dto.CategoryDto;
import ru.practicum.category.model.dto.CategoryRequest;
import ru.practicum.category.model.mapper.CategoryMapper;
import ru.practicum.category.repository.CategoryRepository;
import ru.practicum.exception.ConstraintException;
import ru.practicum.exception.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<CategoryDto> getAll(Integer from, Integer size) {
        PageRequest page = PageRequest.of(from, size, Sort.by("id").ascending());
        List<Category> categories = repository.findAll(page).getContent();
        return categories.stream().map(CategoryMapper::toCategoryDto).toList();
    }

    @Override
    public CategoryDto getById(Long categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id = %d was not found", categoryId))
        );
        return CategoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto create(CategoryRequest categoryRequest) {
        if (repository.findAll().stream().map(Category::getName).anyMatch(name -> name.equals(categoryRequest.getName()))) {
            throw new ConstraintException(String.format("Category with name = %s is already exists", categoryRequest.getName()));
        }
        return CategoryMapper.toCategoryDto(repository.save(CategoryMapper.toCategory(categoryRequest)));
    }

    @Override
    public CategoryDto update(Long categoryId, CategoryRequest categoryRequest) {
        Category category = repository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id = %d was not found", categoryId))
        );
        category.setName(categoryRequest.getName());
        return CategoryMapper.toCategoryDto(repository.save(category));
    }

    @Override
    public void delete(Long categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(() ->
                new NotFoundException(String.format("Category with id = %d was not found", categoryId))
        );
        repository.deleteById(categoryId);
    }
}