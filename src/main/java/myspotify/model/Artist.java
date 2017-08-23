package myspotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = "songs, albums")
public class Artist implements NamedEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Song> songs = new ArrayList<>();

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Album> albums = new ArrayList<>();

    @JsonIgnore
    private String imageFilename;

    public Artist(String name, String imageFilename) {
        this.name = name;
        this.imageFilename = imageFilename;
    }

    // jpa
    Artist() {
    }
}
