package myspotify.config;

import myspotify.model.Album;
import myspotify.model.Artist;
import myspotify.model.Song;
import myspotify.repository.AlbumRepository;
import myspotify.repository.ArtistRepository;
import myspotify.repository.SongRepository;
import myspotify.repository.solr.AlbumSolrRepository;
import myspotify.repository.solr.ArtistSolrRepository;
import myspotify.repository.solr.SongSolrRepository;
import myspotify.service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServiceConfig {

    @Bean
    Service<Song, Long> songService(SongRepository repository, SongSolrRepository solrRepository) {
        return new Service<>(repository, solrRepository);
    }

    @Bean
    Service<Album, Long> albumService(AlbumRepository repository, AlbumSolrRepository solrRepository) {
        return new Service<>(repository, solrRepository);
    }

    @Bean
    Service<Artist, Long> artistService(ArtistRepository repository, ArtistSolrRepository solrRepository) {
        return new Service<>(repository, solrRepository);
    }
}
