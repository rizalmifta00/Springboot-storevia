package com.learn.store.Service.impl;

import com.learn.store.Dto.Category.CategoryDto;
import com.learn.store.Dto.Category.CategorySaveDto;
import com.learn.store.Dto.Category.CategorySubCategorySaveDto;
import com.learn.store.Dto.ResponseDto;
import com.learn.store.Dto.SubCategory.SubCategoryDto;
import com.learn.store.Models.Category;
import com.learn.store.Models.SubCategory;
import com.learn.store.Repository.CategoryRepository;
import com.learn.store.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;



    public List<CategoryDto> findAll(){
       List<Category> categories = categoryRepository.findAll();
       List<CategoryDto> categoryDtos = categories.stream()
               .map(category -> {
                   CategoryDto categoryDto = modelMapper.map(category,CategoryDto.class);
                   List<SubCategoryDto>subCategoryDtos= category.getSubcategories().stream()
                           .map(subCategory -> modelMapper.map(subCategory,SubCategoryDto.class))
                           .collect(Collectors.toList());
                   categoryDto.setSubCategories(subCategoryDtos);
                   return categoryDto;
               })
               .collect(Collectors.toList());
       return categoryDtos;
       


    }

    public CategorySaveDto create(CategorySaveDto categorySaveDto){
        Category category = modelMapper.map(categorySaveDto, Category.class);
        category = categoryRepository.save(category);
        return modelMapper.map(category,CategorySaveDto.class);

    }

    @Override
    public CategorySubCategorySaveDto createCategoryWithSubCategory(CategorySubCategorySaveDto categorySubCategorySaveDto) {
        Category category = modelMapper.map(categorySubCategorySaveDto,Category.class);
        List<SubCategory>subCategories = new ArrayList<>();
        for(SubCategoryDto subCategoryDto : categorySubCategorySaveDto.getSubCategories()){
            SubCategory subCategory = modelMapper.map(subCategoryDto,SubCategory.class);
            subCategory.setCategory(category);
            subCategories.add(subCategory);
        }
        category.setSubcategories(subCategories);
        categoryRepository.save(category);
        return categorySubCategorySaveDto;
    }

    @Override
    public CategorySaveDto update(Long id, CategorySaveDto categorySaveDto) {
        Optional<Category> categoryOptional= categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category category ;
            category = modelMapper.map(categorySaveDto,Category.class);
            category.setId(id);
            return modelMapper.map(categoryRepository.save(category),CategorySaveDto.class);

        }
        return null;
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Category not found with ID: " + id));

        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto findByName(String name) {
        Category category = categoryRepository.findbyNama(name)
                .orElseThrow(()->new EntityNotFoundException("Category not found with Name: " +name));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public CategoryDto delete(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            categoryRepository.delete(category);
            return modelMapper.map(category,CategoryDto.class);
        }
        return null;
    }


}
