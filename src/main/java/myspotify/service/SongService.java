package myspotify.service;

import myspotify.model.Song;
import myspotify.repository.SongRepository;
import myspotify.repository.solr.SongSolrRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class SongService extends Service<Song, Long> {

    private final SongRepository repository;

    @Autowired
    public SongService(SongRepository repository, SongSolrRepository solrRepository) {
        super(repository, solrRepository);
        this.repository = repository;
    }

    public List<Song> findByAlbum(Long albumId) {
        return repository.findByAlbumId(albumId);
    }

    public List<Song> findByArtist(Long artistId) {
        return repository.findByArtistId(artistId);
    }

}
