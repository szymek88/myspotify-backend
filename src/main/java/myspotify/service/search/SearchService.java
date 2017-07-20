package myspotify.service.search;

import myspotify.service.SongService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchService {

    private final SongService songService;

    @Autowired
    public SearchService(SongService songService) {
        this.songService = songService;
    }

    public SearchResults search(String query) throws IOException, SolrServerException {
        SearchResults results = new SearchResults();
        results.setSongs(songService.searchForSong(query));
        return results;
    }

    public Suggestions suggest(String query) throws IOException, SolrServerException {
        Suggestions suggestions = new Suggestions();
        suggestions.setSongSuggestions(songService.suggestSongs(query));
        return suggestions;
    }
}
