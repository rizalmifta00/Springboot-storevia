package com.learn.store.Service;

import com.learn.store.Dto.Category.CategoryDto;
import com.learn.store.Dto.Category.CategorySaveDto;
import com.learn.store.Dto.Category.CategorySubCategorySaveDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto>findAll();
    CategorySaveDto create(CategorySaveDto categorySaveDto);

   CategorySubCategorySaveDto createCategoryWithSubCategory(CategorySubCategorySaveDto categorySubCategorySaveDto);

    CategorySaveDto update(Long id, CategorySaveDto categorySaveDto);

    CategoryDto findById(Long id);

    CategoryDto findByName(String name);

    CategoryDto delete(Long id);
}
