package myspotify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Artist artist;

    @JsonIgnore
    private String filename;

    public Song(String name, Artist artist, String filename) {
        this.name = name;
        this.artist = artist;
        this.filename = filename;
    }

    // jpa
    Song() {
    }
}
