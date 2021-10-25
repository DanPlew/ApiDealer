package daniel.plewinski.apidealer;

import com.fasterxml.jackson.databind.ObjectMapper;
import daniel.plewinski.apidealer.chucknorisjokes.database.entities.User;
import daniel.plewinski.apidealer.chucknorisjokes.database.repositories.UserRepository;
import daniel.plewinski.apidealer.chucknorisjokes.security.roles.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

/**
 * TODO 1: Zaimplementować mappery.
 * TODO 2: Zaimplementować HttpOk.
 * TODO 4: Zaimplementować wszystkie typy relacji dla przećwiczenia.
 */

@SpringBootApplication
public class ApiDealerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDealerApplication.class, args);
    }

    // BEANS
    @Bean
    public CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            User user1 = new User("user", passwordEncoder.encode("qwerty"), "user", UserRole.USER);
            User user2 = new User("admin", passwordEncoder.encode("qwerty"), "admin", UserRole.ADMIN);
            userRepository.save(user1);
            userRepository.save(user2);
        };
    }

    @Bean
    public DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
