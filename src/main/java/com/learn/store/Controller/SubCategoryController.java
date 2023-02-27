package com.learn.store.Controller;

import com.learn.store.Dto.ResponseDto;
import com.learn.store.Dto.SubCategory.SubCategoryDto;
import com.learn.store.Dto.SubCategory.SubCategorySaveDto;
import com.learn.store.Models.SubCategory;
import com.learn.store.Repository.SubCategoryRepository;
import com.learn.store.Service.SubCategoryService;
import com.learn.store.Util.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/subcategory")
@Tag(name = "Sub Category")
public class SubCategoryController {


    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping()
    public ResponseEntity<ResponseDto<List<SubCategoryDto>>> findAll(){
        try {
            ResponseDto<List<SubCategoryDto>> response =
                    ResponseUtil.responseDtoSucces(subCategoryService.findAll(),"Success get All Data Sub Category");
            return new ResponseEntity<>(response ,HttpStatus.OK);

        }catch (Exception e){
            ResponseDto<List<SubCategoryDto>> response =
                    ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseDto<SubCategorySaveDto>> createSubcategory(@Valid @RequestBody SubCategorySaveDto subCategorySaveDto, Errors errors){
        try{
            SubCategorySaveDto subCategory = subCategoryService.create(subCategorySaveDto);
            ResponseDto<SubCategorySaveDto> response = ResponseUtil.responseDtoSucces(subCategory,"SubCategory Succefully Created");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            ResponseDto<SubCategorySaveDto> response = ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<SubCategoryDto>> findById(@PathVariable Long id){
        try{
            ResponseDto<SubCategoryDto> response =
                    ResponseUtil.responseDtoSucces(subCategoryService.findById(id),"Success get data by id = " + id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ResponseDto<SubCategoryDto> response =
                    ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<SubCategorySaveDto>> update(@PathVariable Long id , @RequestBody SubCategorySaveDto subCategorySaveDto){
        try{
            SubCategorySaveDto subCategory = subCategoryService.update(id, subCategorySaveDto);
            ResponseDto<SubCategorySaveDto> response= ResponseUtil.responseDtoSucces(subCategory,"Update Data Success");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (Exception e){
            ResponseDto<SubCategorySaveDto> response =
                    ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<SubCategoryDto>> delete(@PathVariable Long id){
        try{
            SubCategoryDto subCategory = subCategoryService.delete(id);
            ResponseDto<SubCategoryDto> response =
                    ResponseUtil.responseDtoSucces(subCategory,"Success Delete data id = " + id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception e){
            ResponseDto<SubCategoryDto> response =
                    ResponseUtil.responseDtoFailed(null, e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
