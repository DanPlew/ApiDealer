package daniel.plewinski.apidealer.chucknorisjokes.logic.facades;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Category;
import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Joke;
import daniel.plewinski.apidealer.chucknorisjokes.database.entities.User;
import daniel.plewinski.apidealer.chucknorisjokes.logic.facades.interfaces.UserUseCases;
import daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.CategoryMapper;
import daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.JokeMapper;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.CategoryService;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.JokeService;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.UserService;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.CategoryDTO;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.JokeDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFacade implements UserUseCases<JokeDTO> {

    private final JokeService jokeService;
    private final CategoryService categoryService;
    private final UserService userService;

    private final JokeMapper jokeMapper;
    private final CategoryMapper categoryMapper;

    public UserFacade(JokeService jokeService, CategoryService categoryService, UserService userService, JokeMapper jokeMapper, CategoryMapper categoryMapper) {
        this.jokeService = jokeService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.jokeMapper = jokeMapper;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Metoda pokazuje randomowy joke ze strony Chuck Noris Jokes. Użytkownik również musi podać parametr mówiący o tym, czy dany joke zapisać czy też nie.
     * @param save - Czy zapisać joke czy też nie.
     * @return JokeDT - Losowo wybrany joke ze strony.
     */
    @Override
    public JokeDTO showRandomJoke(boolean save) {
        JokeDTO jokeDTO = jokeService.getDataFromAnotherApi();

        if(save){
            List<Category> categoryList = new ArrayList<>();
            for(String categoryName : jokeDTO.getCategories())
                categoryList.add(categoryService.create(
                        categoryMapper.fromDtoToEntity(
                                new CategoryDTO(null, categoryName)
                        )
                ));
            Joke joke = jokeMapper.fromDtoToEntity(jokeDTO);
            joke.setJokeCategoryList(categoryList);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.readByUsername(authentication.getName());
            user.getJokeList().add(joke);

            jokeDTO = jokeMapper.fromEntityToDto(
                    jokeService.create(joke)
            );
        }

        return jokeDTO;
    }

    @Override
    public JokeDTO showMyRandomJoke() {
        return jokeMapper.fromEntityToDto(
                jokeService.getMyRandomJoke()
        );
    }

    @Override
    public void removeMyJoke(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.readByUsername(authentication.getName());
        Joke joke = jokeService.read(id);
        user.getJokeList().remove(joke);
        userService.update(user.getId(), user);
    }

    @Override
    public List<JokeDTO> showAllMyJokes() {
        return jokeMapper.fromEntityListToDtoList(
                jokeService.getAllMyJokes()
        );
    }
}
