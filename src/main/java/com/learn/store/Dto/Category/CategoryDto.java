package com.learn.store.Dto.Category;

import com.learn.store.Dto.SubCategory.SubCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryDto {

    private Long id;
    private String name;
    private List<SubCategoryDto> subCategories = new ArrayList<>();
}
