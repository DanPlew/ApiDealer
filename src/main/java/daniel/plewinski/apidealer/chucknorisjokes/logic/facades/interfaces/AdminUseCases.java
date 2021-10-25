package daniel.plewinski.apidealer.chucknorisjokes.logic.facades.interfaces;

import java.util.List;

public interface AdminUseCases<Dto> {
    List<Dto> showAllCategories(boolean save);
}
