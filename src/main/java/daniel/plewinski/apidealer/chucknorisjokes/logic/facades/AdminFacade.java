package daniel.plewinski.apidealer.chucknorisjokes.logic.facades;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Category;
import daniel.plewinski.apidealer.chucknorisjokes.logic.facades.interfaces.AdminUseCases;
import daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.CategoryMapper;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.CategoryService;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AdminFacade implements AdminUseCases<CategoryDTO> {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public AdminFacade(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> showAllCategories(boolean save) {
        CategoryDTO[] categoryDTOS = categoryService.getDataFromAnotherApi();
        List<CategoryDTO> categoryDTOList = Arrays.asList(categoryDTOS);
        if(save) {
            if(categoryDTOS.length != 0){
                categoryDTOList = categoryMapper.fromEntityListToDtoList(categoryService.createAll(
                        categoryMapper.fromDtoListToEntityList(categoryDTOList)
                ));
            }
        }
        return categoryDTOList;
    }
}
