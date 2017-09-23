package myspotify.service;

import myspotify.model.Playlist;
import myspotify.model.Song;
import myspotify.repository.PlaylistRepository;
import myspotify.repository.SongRepository;
import myspotify.repository.solr.SolrRepository;
import myspotify.repository.solr.SongSolrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class SongService extends Service<Song, Long> {

    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public SongService(SongRepository songRepository, SongSolrRepository solrRepository,
                       PlaylistRepository playlistRepository) {
        super(songRepository, solrRepository);
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }

    public List<Song> findByAlbum(Long albumId) {
        return songRepository.findByAlbumId(albumId);
    }

    public List<Song> findByArtist(Long artistId) {
        return songRepository.findByArtistId(artistId);
    }

    public List<Song> findByPlaylist(Long playlistId) {
        return songRepository.findByPlaylistsId(playlistId);
    }

    public void addSongToPlaylist(Long songId, Long playlistId) {
        Playlist playlist = playlistRepository.findOne(playlistId);
        Song song = songRepository.findOne(songId);
        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

    public void removeSongFromPlaylist(Long songId, Long playlistId) {
        Playlist playlist = playlistRepository.findOne(playlistId);
        Song song = songRepository.findOne(songId);
        playlist.getSongs().remove(song);
        playlistRepository.save(playlist);
    }

}
