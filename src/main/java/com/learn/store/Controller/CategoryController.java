package com.learn.store.Controller;

import com.learn.store.Dto.Category.CategoryDto;
import com.learn.store.Dto.Category.CategorySaveDto;
import com.learn.store.Dto.Category.CategorySubCategorySaveDto;
import com.learn.store.Dto.ResponseDto;
import com.learn.store.Service.CategoryService;
import com.learn.store.Util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<ResponseDto<CategorySaveDto>> createCategory(@RequestBody CategorySaveDto categorySaveDto){
        try{
            CategorySaveDto  category = categoryService.create(categorySaveDto);
            ResponseDto<CategorySaveDto> response = ResponseUtil.responseDtoSucces(category,"Category created Successfully");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            ResponseDto<CategorySaveDto> response = ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<CategoryDto>>> findAll(){
        try{
            ResponseDto<List<CategoryDto>> response =
                    ResponseUtil.responseDtoSucces(categoryService.findAll(),"Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseDto<>(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/categorysub")
    public ResponseEntity<ResponseDto<CategorySubCategorySaveDto>> createCategorySubCategory(@RequestBody CategorySubCategorySaveDto categorySubCategorySaveDto){
        try{
         CategorySubCategorySaveDto category = categoryService.createCategoryWithSubCategory(categorySubCategorySaveDto);
         ResponseDto<CategorySubCategorySaveDto> response = ResponseUtil.responseDtoSucces(category,"success");
         return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            ResponseDto<CategorySubCategorySaveDto> response = ResponseUtil.responseDtoFailed(null,e.getMessage(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

        }
    }





}
