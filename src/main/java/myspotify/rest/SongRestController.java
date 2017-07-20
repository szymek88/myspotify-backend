package myspotify.rest;

import myspotify.hateoas.SongResource;
import myspotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
class SongRestController {

    private final SongService songService;

    @Autowired
    public SongRestController(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(value = "/songs", method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    Resources<SongResource> readSongs() {
        List<SongResource> songResources = songService
                .findAll()
                .stream()
                .map(SongResource::new)
                .collect(toList());

        return new Resources<>(songResources);
    }
}
