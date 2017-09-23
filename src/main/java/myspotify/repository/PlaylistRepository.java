package myspotify.repository;

import myspotify.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("select p from Playlist p where p.user.username = ?1")
    List<Playlist> findByUsername(String username);
}
