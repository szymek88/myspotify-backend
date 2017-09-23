package myspotify.rest;

import myspotify.hateoas.AlbumResource;
import myspotify.hateoas.ArtistResource;
import myspotify.hateoas.SearchResultsResources;
import myspotify.hateoas.SongResource;
import myspotify.service.search.SearchResults;
import myspotify.service.search.SearchService;
import myspotify.service.search.SearchSuggestions;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static myspotify.rest.RestControllerUtils.imitateNetworkDelay;
import static myspotify.rest.RestControllerUtils.mapToResources;

@RestController
class SearchRestController {

    private final SearchService searchService;

    @Autowired
    public SearchRestController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    SearchResultsResources search(@RequestParam("q") String query) throws IOException, SolrServerException {
        SearchResults results = searchService.search(query);
        Resources<SongResource> songsResources = mapToResources(results.songs, song -> new SongResource(song));
        Resources<ArtistResource> artistResources =
                mapToResources(results.artists, artist -> new ArtistResource(artist));
        Resources<AlbumResource> albumsResources = mapToResources(results.albums, album -> new AlbumResource(album));
        imitateNetworkDelay();
        return new SearchResultsResources(songsResources, albumsResources, artistResources);
    }

    @GetMapping("/suggest")
    SearchSuggestions suggest(@RequestParam("q") String query) throws IOException, SolrServerException {
        return searchService.suggest(query);
    }

}
