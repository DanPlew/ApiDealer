package daniel.plewinski.apidealer.chucknorisjokes.logic.services;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Category;
import daniel.plewinski.apidealer.chucknorisjokes.database.repositories.CategoryRepository;
import daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.CategoryMapper;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces.CrudInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements CrudInterface<Category> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category read(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category update(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
