package daniel.plewinski.apidealer.chucknorisjokes.database.repositories;

import daniel.plewinski.apidealer.chucknorisjokes.database.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
}
