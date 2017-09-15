package myspotify.rest;

import myspotify.hateoas.AlbumResource;
import myspotify.model.Album;
import myspotify.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumRestController {

    private final Service<Album, Long> albumService;

    @Autowired
    public AlbumRestController(Service<Album, Long> albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{albumId}")
    public AlbumResource readAlbum(@PathVariable Long albumId) {
        return new AlbumResource(albumService.findOne(albumId));
    }
}
