package myspotify.rest;

import myspotify.hateoas.SongResource;
import myspotify.model.Song;
import myspotify.service.Service;
import myspotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    Resources<SongResource> readSongs() {
        List<SongResource> songsResources = songService
                .findAll()
                .stream()
                .map(SongResource::new)
                .collect(toList());

        return new Resources<>(songsResources);
    }

    @RequestMapping(value = "/album/{albumId}", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
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

    @RequestMapping(value = "/artist/{artistId}", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
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
