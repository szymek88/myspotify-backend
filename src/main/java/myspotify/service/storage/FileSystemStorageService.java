package myspotify.service.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService implements StorageService {

    @Value("${storage.location}")
    private String rootLocation;

    @Override
    public Resource load(String filename) {
        try {
            Path path = Paths.get(rootLocation).resolve(filename);
            Resource file = new UrlResource(path.toUri());
            if (file.exists() || file.isReadable()) {
                return file;
            } else {
                throw new StorageFileNotFoundException("Could not read storage: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read storage: " + filename, e);
        }
    }
}
