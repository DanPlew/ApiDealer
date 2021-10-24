package daniel.plewinski.apidealer.chucknorisjokes.web.controllers;

import daniel.plewinski.apidealer.chucknorisjokes.logic.facades.UserFacade;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.JokeDTO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/getRandomJoke/{save}")
    public JokeDTO getRandomJoke(@PathVariable(name = "save") boolean save){
        return userFacade.showRandomJoke(save);
    }

    @GetMapping("/getMyRandomJoke")
    public JokeDTO getMyRandomJoke(){
        return userFacade.showMyRandomJoke();
    }

    @GetMapping("/getAllMyJokes")
    public List<JokeDTO> getAllMyJokes(){
        return userFacade.showAllMyJokes();
    }

    @DeleteMapping("/deleteJoke/{id}")
    public void deleteMyJokeById(@PathVariable(name = "id") Long id){
        userFacade.removeMyJoke(id);
    }
}
