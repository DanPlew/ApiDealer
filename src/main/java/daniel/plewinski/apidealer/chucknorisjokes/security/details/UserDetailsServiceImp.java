package daniel.plewinski.apidealer.chucknorisjokes.security.details;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.User;
import daniel.plewinski.apidealer.chucknorisjokes.database.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userRepository.findByNickname(nickname).orElse(null);
        if(user == null)
            throw new UsernameNotFoundException("User not found..");
        return new UserDetailsImp(user);
    }
}
