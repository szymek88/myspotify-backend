package myspotify.hateoas;

import lombok.Getter;
import myspotify.model.Album;
import org.springframework.hateoas.ResourceSupport;

public class AlbumResource extends ResourceSupport {

    @Getter
    private Album album;

    public AlbumResource(Album album) {
        this.album = album;
    }
}
