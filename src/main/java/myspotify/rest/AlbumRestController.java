package myspotify.rest;

import myspotify.hateoas.AlbumResource;
import myspotify.model.Album;
import myspotify.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums")
public class AlbumRestController {

    private final Service<Album, Long> albumService;

    @Autowired
    public AlbumRestController(Service<Album, Long> albumService) {
        this.albumService = albumService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",
            produces = "application/json; charset=UTF-8")
    public AlbumResource readAlbum(@PathVariable Long id) {
        return new AlbumResource(albumService.findOne(id));
    }
}
