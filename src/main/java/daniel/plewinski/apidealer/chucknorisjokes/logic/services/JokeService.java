package daniel.plewinski.apidealer.chucknorisjokes.logic.services;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Joke;
import daniel.plewinski.apidealer.chucknorisjokes.database.repositories.JokeRepository;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces.ApiCommunicationInterface;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces.CrudInterface;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.JokeDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class JokeService implements CrudInterface<Joke>, ApiCommunicationInterface<JokeDTO> {

    private final String CHUCK_NORIS_RANDOM_JOKE_URL = "https://api.chucknorris.io/jokes/random";

    private final JokeRepository jokeRepository;
    private final RestTemplate restTemplate;

    public JokeService(JokeRepository jokeRepository, RestTemplate restTemplate) {
        this.jokeRepository = jokeRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public JokeDTO getDataFromAnotherApi() {
        return restTemplate.getForObject(CHUCK_NORIS_RANDOM_JOKE_URL, JokeDTO.class);
    }

    public Joke getMyRandomJoke(){
        return jokeRepository.getRandomJoke().orElse(null);
    }

    public List<Joke> getAllMyJokes(){
        return jokeRepository.findAll();
    }

    @Override
    public Joke create(Joke joke) {
        boolean isGivenJokeInDatabase = jokeRepository
                .getByJoke_id(joke.getJoke_id())
                .orElse(null) != null;

        if(isGivenJokeInDatabase)
            return update(joke.getId(), joke);

        return jokeRepository.save(joke);
    }

    @Override
    public Joke read(Long id) {
        return jokeRepository.findById(id).orElse(null);
    }

    @Override
    public Joke update(Long id, Joke joke) {
        joke.setId(id);
        return jokeRepository.save(joke);
    }

    @Override
    public void delete(Long id) {
        jokeRepository.findById(id).ifPresent(jokeRepository::delete);
    }
}
