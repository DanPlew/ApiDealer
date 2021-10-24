package daniel.plewinski.apidealer.chucknorisjokes.logic.services;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.User;
import daniel.plewinski.apidealer.chucknorisjokes.database.repositories.UserRepository;
import daniel.plewinski.apidealer.chucknorisjokes.logic.services.interfaces.CrudInterface;
import org.springframework.stereotype.Service;

@Service
public class UserService implements CrudInterface<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readByUsername(String username){
        return userRepository.findByNickname(username).orElse(null);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }
}
