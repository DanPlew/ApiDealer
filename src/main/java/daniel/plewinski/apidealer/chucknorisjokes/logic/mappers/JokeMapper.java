package daniel.plewinski.apidealer.chucknorisjokes.logic.mappers;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Category;
import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Joke;
import daniel.plewinski.apidealer.chucknorisjokes.logic.mappers.interfaces.MappingInterface;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.JokeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JokeMapper implements MappingInterface<Joke, JokeDTO> {

    @Autowired
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public JokeDTO fromEntityToDto(Joke joke) {
        if(joke == null)
            return null;

        return new JokeDTO(
                joke.getId(),
                fromCategoryToStringArray(joke.getJokeCategoryList()),
                joke.getJoke_created_at().toString(),
                joke.getIcon_url(),
                joke.getJoke_id(),
                joke.getJoke_updated_at().toString(),
                joke.getUrl(),
                joke.getJoke()
        );
    }

    @Override
    public Joke fromDtoToEntity(JokeDTO jokeDTO) {
        if(jokeDTO == null)
            return null;

        List<Category> categories = new ArrayList<>();
        for(String category : jokeDTO.getCategories())
            categories.add(new Category(category));

        Joke joke =  new Joke(
                fromStringToLocalDateTime(jokeDTO.getCreated_at()),
                fromStringToLocalDateTime(jokeDTO.getUpdated_at()),
                LocalDateTime.now(),
                jokeDTO.getIcon_url(),
                jokeDTO.getId(),
                jokeDTO.getUrl(),
                jokeDTO.getValue());
        joke.setId(jokeDTO.getDb_id());
        joke.setJokeCategoryList(categories);

        return joke;
    }

    @Override
    public List<JokeDTO> fromEntityListToDtoList(List<Joke> jokes) {
        if(jokes == null || jokes.isEmpty())
            return null;

        List<JokeDTO> dtoJokeList = new ArrayList<>();
        jokes.forEach(joke -> dtoJokeList.add(
                fromEntityToDto(joke)
        ));
        return dtoJokeList;
    }

    @Override
    public List<Joke> fromDtoListToEntityList(List<JokeDTO> jokeDTOS) {
        if(jokeDTOS == null || jokeDTOS.isEmpty())
            return null;

        List<Joke> jokeList = new ArrayList<>();
        jokeDTOS.forEach(jokeDTO -> jokeList.add(
                fromDtoToEntity(jokeDTO)
        ));
        return jokeList;
    }

    private String[] fromCategoryToStringArray(List<Category> categoryList) {
        String[] categories = new String[categoryList.size()];

        for (int i = 0; i < categories.length; i++)
            categories[i] = categoryList.get(i).getName();

        return categories;
    }

    private LocalDateTime fromStringToLocalDateTime(String dateTime) {
        //2020-01-05 13:42:28.664997
        String[] dateAndTime = dateTime
                .split("\\.")[0]
                .split(" ");
        return LocalDateTime.parse(dateAndTime[0] + " " + dateAndTime[1], dateTimeFormatter);
    }
}
