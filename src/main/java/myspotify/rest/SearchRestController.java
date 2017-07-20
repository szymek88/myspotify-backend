package myspotify.rest;

import myspotify.hateoas.SearchResultsResources;
import myspotify.service.search.SearchResults;
import myspotify.service.search.SearchService;
import myspotify.service.search.Suggestions;
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

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    SearchResultsResources search(@RequestParam("q") String query) throws IOException, SolrServerException {
        SearchResults results = searchService.search(query);
        return new SearchResultsResources(results);
    }

    @RequestMapping(value = "/suggest", method = RequestMethod.GET)
    Suggestions suggest(@RequestParam("q") String query) throws IOException, SolrServerException {
        return searchService.suggest(query);
    }
}
