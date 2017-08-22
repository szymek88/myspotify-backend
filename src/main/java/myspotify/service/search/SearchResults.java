package myspotify.service.search;

import myspotify.model.Album;
import myspotify.model.Artist;
import myspotify.model.Song;

import java.util.List;

public class SearchResults {

    public final List<Song> songs;
    public final List<Album> albums;
    public final List<Artist> artists;

    public SearchResults(List<Song> songs, List<Album> albums, List<Artist> artists) {
        this.songs = songs;
        this.albums = albums;
        this.artists = artists;
    }
}
