package myspotify.rest;

import myspotify.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AudioFileRestController {

    private final StorageService storageService;

    @Autowired
    public AudioFileRestController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value = "/audio/{filename}", method = RequestMethod.GET)
    public ResponseEntity<Resource> serveAudioFile(@PathVariable String filename) {

        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .contentType(new MediaType("audio", "mpeg"))
                .body(file);
    }

}
