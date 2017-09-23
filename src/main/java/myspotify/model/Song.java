package myspotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Song implements NamedEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    @JsonIgnore
    private Album album;

    @JsonIgnore
    private String audioFilename;

    @JsonIgnore
    private String imageFilename;

    @ManyToMany(mappedBy = "songs")
    @JsonIgnore
    private List<Playlist> playlists = new ArrayList<>();

    public Song(String name, Artist artist, Album album,
                String audioFilename, String imageFilename) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.audioFilename = audioFilename;
        this.imageFilename = imageFilename;
    }

    // jpa
    Song() {
    }
}
