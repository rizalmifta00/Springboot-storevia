package com.learn.store.Service;

import com.learn.store.Dto.Category.CategoryDto;
import com.learn.store.Dto.Category.CategorySaveDto;
import com.learn.store.Dto.Category.CategorySubCategorySaveDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto>findAll();
    CategorySaveDto create(CategorySaveDto categorySaveDto);

   CategorySubCategorySaveDto createCategoryWithSubCategory(CategorySubCategorySaveDto categorySubCategorySaveDto);

    CategoryDto update(Long id, CategoryDto categoryDto);
}
