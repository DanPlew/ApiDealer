package daniel.plewinski.apidealer.chucknorisjokes.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime joke_created_at;
    private LocalDateTime joke_updated_at;
    private LocalDateTime joke_added_at;
    private String icon_url;
    private String joke_id;
    private String url;
    private String joke;

    @ManyToMany
    @JoinTable(
            name = "joke_category",
            joinColumns = @JoinColumn(name = "joke_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> jokeCategoryList;

    @ManyToMany(mappedBy = "jokeList")
    private List<User> userList;

    public Joke() {
    }

    public Joke(LocalDateTime joke_created_at, LocalDateTime joke_updated_at, LocalDateTime joke_added_at, String icon_url, String joke_id, String url, String joke) {
        this.joke_created_at = joke_created_at;
        this.joke_updated_at = joke_updated_at;
        this.joke_added_at = joke_added_at;
        this.icon_url = icon_url;
        this.joke_id = joke_id;
        this.url = url;
        this.joke = joke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getJoke_created_at() {
        return joke_created_at;
    }

    public void setJoke_created_at(LocalDateTime joke_created_at) {
        this.joke_created_at = joke_created_at;
    }

    public LocalDateTime getJoke_updated_at() {
        return joke_updated_at;
    }

    public void setJoke_updated_at(LocalDateTime joke_updated_at) {
        this.joke_updated_at = joke_updated_at;
    }

    public LocalDateTime getJoke_added_at() {
        return joke_added_at;
    }

    public void setJoke_added_at(LocalDateTime joke_added_at) {
        this.joke_added_at = joke_added_at;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getJoke_id() {
        return joke_id;
    }

    public void setJoke_id(String joke_id) {
        this.joke_id = joke_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<Category> getJokeCategoryList() {
        return jokeCategoryList;
    }

    public void setJokeCategoryList(List<Category> jokeCategoryList) {
        this.jokeCategoryList = jokeCategoryList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
