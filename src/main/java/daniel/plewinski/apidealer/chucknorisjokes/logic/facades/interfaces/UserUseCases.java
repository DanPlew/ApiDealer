package daniel.plewinski.apidealer.chucknorisjokes.logic.facades.interfaces;

import java.util.List;

public interface UserUseCases<Dto> {
    Dto showRandomJoke(boolean save);
    Dto showMyRandomJoke();
    void removeMyJoke(Long id);
    List<Dto> showAllMyJokes();
}
