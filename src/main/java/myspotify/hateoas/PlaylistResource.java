package myspotify.hateoas;

import lombok.Getter;
import myspotify.model.Playlist;
import myspotify.rest.PlaylistRestController;
import myspotify.rest.SongRestController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PlaylistResource extends ResourceSupport {

    @Getter
    private Playlist playlist;

    public PlaylistResource(Playlist playlist) {
        this.playlist = playlist;
        this.add(linkTo(methodOn(PlaylistRestController.class)
                .readPlaylist(playlist.getId())).withSelfRel());
        this.add(linkTo(methodOn(SongRestController.class)
                .readPlaylistSongs(playlist.getId())).withRel("songs"));
    }
}
