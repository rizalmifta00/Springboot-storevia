package com.learn.store.Service.impl;

import com.learn.store.Dto.SubCategory.SubCategoryDto;
import com.learn.store.Dto.SubCategory.SubCategorySaveDto;
import com.learn.store.Models.Category;
import com.learn.store.Models.SubCategory;
import com.learn.store.Repository.CategoryRepository;
import com.learn.store.Repository.SubCategoryRepository;
import com.learn.store.Service.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<SubCategoryDto> findAll() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();
        List<SubCategoryDto> subCategoryDtos = new ArrayList<>();
        for (SubCategory subCategory : subCategories){
            subCategoryDtos.add(modelMapper.map(subCategory,SubCategoryDto.class));
        }

        return subCategoryDtos;
    }

    @Override
    public SubCategorySaveDto create(SubCategorySaveDto subCategorySaveDto) {
        Category category = categoryRepository.findById(subCategorySaveDto.getCategoryId())
                .orElseThrow(()->new EntityNotFoundException("Category not found with ID = " + subCategorySaveDto.getCategoryId()));
        SubCategory subCategory = modelMapper.map(subCategorySaveDto,SubCategory.class);
        subCategorySaveDto.setCategoryId(category.getId());
        subCategory = subCategoryRepository.save(subCategory);
        return modelMapper.map(subCategory,SubCategorySaveDto.class);

    }

    @Override
    public SubCategoryDto findById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Sub Category with Id = " + id));

        return modelMapper.map(subCategory,SubCategoryDto.class);
    }

    @Override
    public SubCategorySaveDto update(Long id,SubCategorySaveDto subCategorySaveDto) {
        Category category = categoryRepository.findById(subCategorySaveDto.getCategoryId())
                .orElseThrow(()->new EntityNotFoundException("Category not found with id + " + subCategorySaveDto.getCategoryId()));
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Sub category not found with id = "+ id));

        SubCategory subCategories = modelMapper.map(subCategorySaveDto,SubCategory.class);
        subCategorySaveDto.setCategoryId(category.getId());
        subCategories.setId(id);
        subCategories = subCategoryRepository.save(subCategories);
        return modelMapper.map(subCategories,SubCategorySaveDto.class);
    }

    @Override
    public SubCategoryDto delete(Long id) {
        Optional<SubCategory> subCategoryOptional = subCategoryRepository.findById(id);
        if(subCategoryOptional.isPresent()){
            SubCategory subCategory = subCategoryOptional.get();
            subCategoryRepository.delete(subCategory);
            return modelMapper.map(subCategory,SubCategoryDto.class);
        }
        return null;
    }


}
