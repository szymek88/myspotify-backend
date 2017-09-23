package myspotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Playlist> playlists;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // jpa
    User() {
    }
}
