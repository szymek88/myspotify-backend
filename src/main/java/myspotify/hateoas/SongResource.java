package myspotify.hateoas;

import lombok.Getter;
import myspotify.model.Song;
import myspotify.rest.AlbumRestController;
import myspotify.rest.AudioFileRestController;
import myspotify.rest.ImageRestController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SongResource extends ResourceSupport {

    @Getter
    private Song song;

    public SongResource(Song song) {
        this.song = song;
        String audioFilename = song.getAudioFilename();
        String imageFilename = song.getImageFilename();
        this.add(linkTo(methodOn(AudioFileRestController.class)
                .loadAudioFile(audioFilename)).withRel("audio"));
        this.add(linkTo(methodOn(ImageRestController.class)
                .loadImage(imageFilename)).withRel("image"));
        this.add(linkTo(methodOn(AlbumRestController.class)
                .readAlbum(song.getAlbum().getId())).withRel("album"));
    }
}
