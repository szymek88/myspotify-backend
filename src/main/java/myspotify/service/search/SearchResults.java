package myspotify.service.search;

import lombok.Data;
import myspotify.model.Song;

import java.util.List;

@Data
public class SearchResults {

    private List<Song> songs;

    public SearchResults() {}

}
