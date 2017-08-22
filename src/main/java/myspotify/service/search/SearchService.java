package myspotify.service.search;

import myspotify.model.Album;
import myspotify.model.Artist;
import myspotify.model.Song;
import myspotify.service.Service;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Service
public class SearchService {

    private final Service<Song, Long> songService;
    private final Service<Album, Long> albumService;
    private final Service<Artist, Long> artistService;

    @Autowired
    public SearchService(Service<Song, Long> songService, Service<Album, Long> albumService,
                         Service<Artist, Long> artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    public SearchResults search(String query) throws IOException, SolrServerException {
        List<Song> songs = songService.search(query);
        List<Album> albums = albumService.search(query);
        List<Artist> artists = artistService.search(query);
        return new SearchResults(songs, albums, artists);
    }

    public SearchSuggestions suggest(String query) throws IOException, SolrServerException {
        List<String> songSuggestions = songService.suggest(query);
        List<String> albumSuggestions = albumService.suggest(query);
        List<String> artistSuggestions = artistService.suggest(query);
        return new SearchSuggestions(songSuggestions, albumSuggestions, artistSuggestions);
    }
}
