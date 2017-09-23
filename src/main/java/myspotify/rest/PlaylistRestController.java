package myspotify.rest;

import myspotify.hateoas.PlaylistResource;
import myspotify.model.Playlist;
import myspotify.model.User;
import myspotify.repository.PlaylistRepository;
import myspotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

import static myspotify.rest.RestControllerUtils.mapToResources;

@RestController
@RequestMapping("/playlists")
public class PlaylistRestController {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlaylistRestController(PlaylistRepository playlistRepository,
                                  UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(produces = "application/json")
    Resources<PlaylistResource> readPlaylists(Principal principal) {
        return mapToResources(playlistRepository.findByUsername(principal.getName()),
                playlist -> new PlaylistResource(playlist));
    }

    @PostMapping(produces = "application/json")
    ResponseEntity addPlaylist(Principal principal, @RequestBody Playlist playlist) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        playlist.setUser(user);
        Playlist savedPlaylist = playlistRepository.save(playlist);

        Link playlistSelfLink = new PlaylistResource(savedPlaylist).getLink("self");
        return ResponseEntity.created(URI.create(playlistSelfLink.getHref()))
                .body(new PlaylistResource(savedPlaylist));
    }

    @GetMapping(value = "/{playlistId}", produces = "application/json")
    public PlaylistResource readPlaylist(@PathVariable Long playlistId) {
        return new PlaylistResource(playlistRepository.findOne(playlistId));
    }

    @DeleteMapping("/{playlistId}")
    ResponseEntity deletePlaylist(@PathVariable Long playlistId) {
        playlistRepository.delete(playlistId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
