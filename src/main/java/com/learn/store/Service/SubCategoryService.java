package com.learn.store.Service;

import com.learn.store.Dto.SubCategory.SubCategoryDto;
import com.learn.store.Dto.SubCategory.SubCategorySaveDto;

import java.util.List;

public interface SubCategoryService {

    List<SubCategoryDto> findAll();

    SubCategorySaveDto create(SubCategorySaveDto subCategorySaveDto);

    SubCategoryDto findById(Long id);

    SubCategorySaveDto update (Long id,SubCategorySaveDto subCategorySaveDto);

    SubCategoryDto delete(Long id);

}
