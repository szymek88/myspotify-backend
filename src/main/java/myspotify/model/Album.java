package myspotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "songs")
public class Album implements NamedEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Artist artist;

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private List<Song> songs = new ArrayList<>();

    public Album(String name, Artist artist) {
        this.name = name;
        this.artist = artist;
    }

    // required by entity classes
    Album() {}
}
