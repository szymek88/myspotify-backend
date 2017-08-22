package myspotify.hateoas;

import lombok.Getter;
import myspotify.model.Artist;
import org.springframework.hateoas.ResourceSupport;

class ArtistResource extends ResourceSupport {

    @Getter
    private Artist artist;

    public ArtistResource(Artist artist) {
        this.artist = artist;
    }
}
