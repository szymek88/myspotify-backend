package myspotify.repository;

import myspotify.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByAlbumId(Long albumId);
    List<Song> findByArtistId(Long artistId);
    List<Song> findByPlaylistsId(Long playlistId);
}
