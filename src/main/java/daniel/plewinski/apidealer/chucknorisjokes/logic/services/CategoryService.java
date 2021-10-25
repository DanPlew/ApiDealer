package daniel.plewinski.apidealer.chucknorisjokes.logic.services;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Category;
import daniel.plewinski.apidealer.chucknorisjokes.database.repositories.CategoryRepository;
import daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.CategoryMapper;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces.ApiCommunicationInterface;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces.CrudInterface;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.CategoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryService implements CrudInterface<Category>, ApiCommunicationInterface<CategoryDTO[]> {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final RestTemplate restTemplate;

    private final String CHUCK_NORIS_JOKE_CATEGORIES_URL = "https://api.chucknorris.io/jokes/categories";

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, RestTemplate restTemplate) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public CategoryDTO[] getDataFromAnotherApi() {
        return restTemplate.getForObject(CHUCK_NORIS_JOKE_CATEGORIES_URL, CategoryDTO[].class);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> createAll(List<Category> categoryList){
        return categoryRepository.saveAll(categoryList);
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
