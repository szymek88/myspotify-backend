package myspotify.rest;

import myspotify.hateoas.SongResource;
import myspotify.model.Song;
import myspotify.service.Service;
import myspotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/songs")
public class SongRestController {

    private final SongService songService;

    @Autowired
    public SongRestController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping(value = "/album/{albumId}", produces = "application/json")
    public Resources<SongResource> readSongsFromAlbum(@PathVariable Long albumId) {
        List<SongResource> songsResources = songService
                .findByAlbum(albumId)
                .stream()
                .map(SongResource::new)
                .collect(toList());

        // Imitate network delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Resources<>(songsResources);
    }

    @GetMapping(value = "/artist/{artistId}", produces = "application/json")
    public Resources<SongResource> readSongsOfArtist(@PathVariable Long artistId) {
        List<SongResource> songsResources = songService
                .findByAlbum(artistId)
                .stream()
                .map(SongResource::new)
                .collect(toList());

        // Imitate network delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Resources<>(songsResources);
    }
}
