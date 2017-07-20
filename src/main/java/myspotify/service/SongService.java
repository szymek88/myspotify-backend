package myspotify.service;

import myspotify.model.Song;
import myspotify.repository.SongRepository;
import myspotify.repository.solr.SongSolrRepository;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final SongSolrRepository songSolrRepository;

    @Autowired
    public SongService(SongRepository songRepository, SongSolrRepository songSolrRepository) {
        this.songRepository = songRepository;
        this.songSolrRepository = songSolrRepository;
    }

    @Transactional
    public Song save(Song song) throws IOException, SolrServerException {
        Song savedSong = songRepository.save(song);
        songSolrRepository.indexSong(savedSong);
        return savedSong;
    }

    public Song findOne(Long id) {
        return songRepository.findOne(id);
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public List<Song> searchForSong(String query) throws IOException, SolrServerException {
        return songSolrRepository.searchForSong(query);
    }

    public List<String> suggestSongs(String query) throws IOException, SolrServerException {
        return songSolrRepository.suggestSongs(query);
    }
}
