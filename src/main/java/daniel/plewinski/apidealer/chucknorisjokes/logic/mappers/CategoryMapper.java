package daniel.plewinski.apidealer.chucknorisjokes.logic.mappers;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Category;
import daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.interfaces.MappingInterface;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.CategoryDTO;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.JokeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper implements MappingInterface<Category, CategoryDTO> {

    @Override
    public CategoryDTO fromEntityToDto(Category category) {
        if(category == null)
            return null;

        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }

    @Override
    public List<CategoryDTO> fromEntityListToDtoList(List<Category> categories) {
        if(categories == null || categories.isEmpty())
            return null;

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categories.forEach(joke -> categoryDTOS.add(
                fromEntityToDto(joke)
        ));
        return categoryDTOS;
    }

    @Override
    public Category fromDtoToEntity(CategoryDTO categoryDTO) {
        if(categoryDTO == null)
            return null;

        Category category = new Category(
                categoryDTO.getName()
        );
        category.setId(categoryDTO.getId());

        return category;
    }

    @Override
    public List<Category> fromDtoListToEntityList(List<CategoryDTO> categoryDTOS) {
        if(categoryDTOS == null || categoryDTOS.isEmpty())
            return null;

        List<Category> categories = new ArrayList<>();
        categoryDTOS.forEach(joke -> categories.add(
                fromDtoToEntity(joke)
        ));
        return categories;
    }
}
