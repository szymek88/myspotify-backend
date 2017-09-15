package myspotify.rest;

import myspotify.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageRestController {

    private final StorageService storageService;

    @Value("${storage.images}")
    private String rootLocation;

    @Autowired
    public ImageRestController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> loadImage(@PathVariable String filename) {

        Resource image = storageService.load(filename, rootLocation);
        return ResponseEntity.ok()
                .contentType(new MediaType("image", "jpeg"))
                .body(image);
    }
}
