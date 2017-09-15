package myspotify.rest;

import myspotify.hateoas.SearchResultsResources;
import myspotify.service.search.SearchResults;
import myspotify.service.search.SearchService;
import myspotify.service.search.SearchSuggestions;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        // Imitate network delay
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchResultsResources(results);
    }

    @GetMapping("/suggest")
    SearchSuggestions suggest(@RequestParam("q") String query) throws IOException, SolrServerException {
        return searchService.suggest(query);
    }
}
