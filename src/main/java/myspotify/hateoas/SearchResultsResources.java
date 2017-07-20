package myspotify.hateoas;

import lombok.Getter;
import myspotify.service.search.SearchResults;
import org.springframework.hateoas.Resources;

import java.util.List;

import static java.util.stream.Collectors.*;

public class SearchResultsResources {

    @Getter
    private Resources<SongResource> songResources;

    public SearchResultsResources(SearchResults results) {
        List<SongResource> songResourceList = results.getSongs()
                .stream()
                .map(SongResource::new)
                .collect(toList());

        songResources = new Resources<>(songResourceList);
    }
}
