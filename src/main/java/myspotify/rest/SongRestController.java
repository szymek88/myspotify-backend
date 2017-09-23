package myspotify.rest;

import myspotify.hateoas.SongResource;
import myspotify.repository.PlaylistRepository;
import myspotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static myspotify.rest.RestControllerUtils.*;
import static myspotify.rest.RestControllerUtils.imitateNetworkDelay;

@RestController
@RequestMapping("/songs")
public class SongRestController {

    private final SongService songService;

    @Autowired
    public SongRestController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/album/{albumId}", produces = "application/json")
    public Resources<SongResource> readAlbumSongs(@PathVariable Long albumId) {
        imitateNetworkDelay();
        return mapToResources(songService.findByAlbum(albumId), song -> new SongResource(song));
    }

    @GetMapping(value = "/artist/{artistId}", produces = "application/json")
    public Resources<SongResource> readArtistSongs(@PathVariable Long artistId) {
        imitateNetworkDelay();
        return mapToResources(songService.findByArtist(artistId), song -> new SongResource(song));
    }

    @GetMapping(value = "/playlist/{playlistId}", produces = "application/json")
    public Resources<SongResource> readPlaylistSongs(@PathVariable Long playlistId) {
        return mapToResources(songService.findByPlaylist(playlistId), song -> new SongResource(song));
    }

    @PostMapping("/{songId}/{playlistId}")
    ResponseEntity addSongToPlaylist(@PathVariable Long songId, @PathVariable Long playlistId) {
        songService.addSongToPlaylist(songId, playlistId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{songId}/{playlistId}")
    ResponseEntity removeSongFromPlaylist(@PathVariable Long songId, @PathVariable Long playlistId) {
        songService.removeSongFromPlaylist(songId, playlistId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
