package myspotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Playlist {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private User user;

    public Playlist(String name, User user) {
        this.name = name;
        this.user = user;
    }

    Playlist() {}
}
