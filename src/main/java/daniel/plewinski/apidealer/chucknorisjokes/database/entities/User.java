package daniel.plewinski.apidealer.chucknorisjokes.database.entities;

import daniel.plewinski.apidealer.chucknorisjokes.security.roles.UserRole;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String nickname;
    private UserRole userRole;

    @ManyToMany
    @JoinTable(
            name = "user_joke",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "joke_id")
    )
    private List<Joke> jokeList;

    public User(){}

    public User(String login, String password, String nickname, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Joke> getJokeList() {
        return jokeList;
    }

    public void setJokeList(List<Joke> jokeList) {
        this.jokeList = jokeList;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userRole=" + userRole +
                ", jokeList=" + jokeList +
                '}';
    }
}
