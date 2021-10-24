package daniel.plewinski.apidealer.chucknorisjokes.web.models;


public class JokeDTO {

    private Long db_id;
    private String[] categories;
    private String created_at;
    private String icon_url;
    private String id;
    private String updated_at;
    private String url;
    private String value;

    public JokeDTO() {}

    public JokeDTO(Long db_id, String[] categories, String created_at, String icon_url, String id, String updated_at, String url, String value) {
        this.db_id = db_id;
        this.categories = categories;
        this.created_at = created_at;
        this.icon_url = icon_url;
        this.id = id;
        this.updated_at = updated_at;
        this.url = url;
        this.value = value;
    }

    public Long getDb_id() {
        return db_id;
    }

    public void setDb_id(Long db_id) {
        this.db_id = db_id;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
