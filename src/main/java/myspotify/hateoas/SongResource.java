package myspotify.hateoas;

import lombok.Getter;
import myspotify.model.Song;
import myspotify.rest.AudioFileRestController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SongResource extends ResourceSupport {

    @Getter
    private Song song;

    public SongResource(Song song) {
        this.song = song;
        String filename = song.getFilename();
        this.add(linkTo(methodOn(AudioFileRestController.class, filename)
                .serveAudioFile(filename)).withRel("audio"));
    }
}
