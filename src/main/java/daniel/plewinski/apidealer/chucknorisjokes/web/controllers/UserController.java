package daniel.plewinski.apidealer.chucknorisjokes.web.controllers;

import daniel.plewinski.apidealer.chucknorisjokes.logic.facades.interfaces.UserUseCases;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.JokeDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserUseCases<JokeDTO> userUseCases;

    public UserController(UserUseCases userUseCases) {
        this.userUseCases = userUseCases;
    }

    @GetMapping("/getRandomJoke/{save}")
    public JokeDTO getRandomJoke(@PathVariable(name = "save") boolean save) {
        return userUseCases.showRandomJoke(save);
    }

    @GetMapping("/getMyRandomJoke")
    public JokeDTO getMyRandomJoke() {
        return userUseCases.showMyRandomJoke();
    }

    @GetMapping("/getAllMyJokes")
    public List<JokeDTO> getAllMyJokes() {
        return userUseCases.showAllMyJokes();
    }

    @DeleteMapping("/deleteJoke/{id}")
    public void deleteMyJokeById(@PathVariable(name = "id") Long id) {
        userUseCases.removeMyJoke(id);
    }
}
