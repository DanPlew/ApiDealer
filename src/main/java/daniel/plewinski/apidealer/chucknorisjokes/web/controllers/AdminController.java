package daniel.plewinski.apidealer.chucknorisjokes.web.controllers;

import daniel.plewinski.apidealer.chucknorisjokes.logic.facades.AdminFacade;
import daniel.plewinski.apidealer.chucknorisjokes.web.models.CategoryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private final AdminFacade adminFacade;

    public AdminController(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    @GetMapping("/getAllCategories/{save}")
    public List<CategoryDTO> getAllCategories(@PathVariable(name = "save") boolean save){
        return adminFacade.showAllCategories(save);
    }
}