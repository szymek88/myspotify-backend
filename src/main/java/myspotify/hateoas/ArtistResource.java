package myspotify.hateoas;

import lombok.Getter;
import myspotify.model.Artist;
import myspotify.rest.ImageRestController;
import myspotify.rest.SongRestController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ArtistResource extends ResourceSupport {

    @Getter
    private Artist artist;

    public ArtistResource(Artist artist) {
        this.artist = artist;
        String imageFilename = artist.getImageFilename();
        this.add(linkTo(methodOn(ImageRestController.class)
                .loadImage(imageFilename)).withRel("image"));
        Long artistId = artist.getId();
        this.add(linkTo(methodOn(SongRestController.class)
                .readArtistSongs(artistId)).withRel("songs"));
    }
}
