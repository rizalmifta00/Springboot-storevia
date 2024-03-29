package com.learn.store.Dto.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorySaveDto {
    @NotEmpty(message = "Name is Required")
    private String name;
}
