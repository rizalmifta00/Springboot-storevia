package com.learn.store.Dto.SubCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategorySaveDto {

    @NotEmpty(message = "name is required") @NonNull
    private String name;

    private Long categoryId;
}
