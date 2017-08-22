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

    public SearchResultsResources(SearchResults results) {
        songsResources = mapToResources(results.songs, song -> new SongResource(song));
        albumsResources = mapToResources(results.albums, album -> new AlbumResource(album));
        artistsResources = mapToResources(results.artists, artist -> new ArtistResource(artist));
    }

    private <Entity, Resource extends ResourceSupport> Resources<Resource>
    mapToResources(List<Entity> entities, ResourceMapper<Entity, Resource> mapper) {
        List<Resource> resources = entities
                .stream()
                .map(mapper::mapToResource)
                .collect(toList());

        return new Resources<>(resources);
    }

}
