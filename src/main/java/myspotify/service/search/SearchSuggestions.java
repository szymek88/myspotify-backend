package myspotify.service.search;

import java.util.List;

public class SearchSuggestions {

    public final List<String> songSuggestions;
    public final List<String> albumSuggestions;
    public final List<String> artistSuggestions;

    public SearchSuggestions(List<String> songSuggestions, List<String> albumSuggestions,
                             List<String> artistSuggestions) {
        this.songSuggestions = songSuggestions;
        this.albumSuggestions = albumSuggestions;
        this.artistSuggestions = artistSuggestions;
    }
}
