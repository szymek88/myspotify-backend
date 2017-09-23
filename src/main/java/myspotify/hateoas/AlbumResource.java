package myspotify.hateoas;

import lombok.Getter;
import myspotify.model.Album;
import myspotify.rest.ImageRestController;
import myspotify.rest.SongRestController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class AlbumResource extends ResourceSupport {

    @Getter
    private Album album;

    public AlbumResource(Album album) {
        this.album = album;
        String imageFilename = album.getImageFilename();
        this.add(linkTo(methodOn(ImageRestController.class)
                .loadImage(imageFilename)).withRel("image"));
        Long albumId = album.getId();
        this.add(linkTo(methodOn(SongRestController.class)
                .readAlbumSongs(albumId)).withRel("songs"));
    }
}
