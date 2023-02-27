package com.learn.store.Controller;

import com.learn.store.Dto.Category.CategoryDto;
import com.learn.store.Dto.Category.CategorySaveDto;
import com.learn.store.Dto.Category.CategorySubCategorySaveDto;
import com.learn.store.Dto.ResponseDto;
import com.learn.store.Models.Category;
import com.learn.store.Service.CategoryService;
import com.learn.store.Util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/category")
@Tag(name = "Category" , description = "Get API CATEGORY")
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
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<CategoryDto>> findById(@PathVariable Long id){
        try {
            ResponseDto<CategoryDto> response =
                    ResponseUtil.responseDtoSucces(categoryService.findById(id),"success get data category");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ResponseDto<CategoryDto> response =
                    ResponseUtil.responseDtoFailed(null,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<CategoryDto>> findByName(@RequestParam String name){
        try{
            ResponseDto<CategoryDto> response =
                    ResponseUtil.responseDtoSucces(categoryService.findByName(name),"success get data " + name);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ResponseDto<CategoryDto> response =
                    ResponseUtil.responseDtoFailed(null,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<CategorySaveDto>> updateCategory(@PathVariable Long id , @RequestBody CategorySaveDto categorySaveDto){
        try{
            CategorySaveDto category = categoryService.update(id, categorySaveDto);
            ResponseDto<CategorySaveDto> response = ResponseUtil.responseDtoSucces(category,"succes update data");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            ResponseDto<CategorySaveDto> response = ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<CategoryDto>> delete(@PathVariable long id){
        try{
            CategoryDto category = categoryService.delete(id);
            ResponseDto<CategoryDto> response = ResponseUtil.responseDtoSucces(category,"succes delete data id :" + category.getName());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ResponseDto<CategoryDto> response = ResponseUtil.responseDtoFailed(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }





}
