package daniel.plewinski.apidealer.chucknorisjokes.database.repositories;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

    @Query("select joke from Joke joke where joke.joke_id = ?1")
    Optional<Joke> getByJoke_id(String joke_id);

    @Query(value = "select * from joke order by rand() limit 1", nativeQuery = true)
    Optional<Joke> getRandomJoke();
}
