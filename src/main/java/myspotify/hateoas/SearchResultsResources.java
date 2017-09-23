package myspotify.hateoas;

import lombok.Getter;
import myspotify.service.search.SearchResults;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

import java.util.List;

import static java.util.stream.Collectors.*;

public class SearchResultsResources {

    @Getter
    private Resources<SongResource> songsResources;

    @Getter
    private Resources<AlbumResource> albumsResources;

    @Getter
    private Resources<ArtistResource> artistsResources;

    public SearchResultsResources(Resources<SongResource> songsResources,
                                  Resources<AlbumResource> albumsResources,
                                  Resources<ArtistResource> artistsResources) {
        this.songsResources = songsResources;
        this.albumsResources = albumsResources;
        this.artistsResources = artistsResources;
    }
}
