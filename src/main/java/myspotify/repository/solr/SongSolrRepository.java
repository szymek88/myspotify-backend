package myspotify.repository.solr;

import myspotify.model.Song;
import myspotify.repository.SongRepository;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class SongSolrRepository extends SolrRepository<Song, Long> {

    @Value("${solr.cores.songs}")
    private String coreName;

    @Autowired
    public SongSolrRepository(SolrClient solrClient, SongRepository songRepository) {
        super(solrClient, songRepository);
    }

    protected SolrInputDocument getFilledDocument(Song song) {
        SolrInputDocument document = super.getFilledDocument(song);
        document.addField("artistName", song.getArtist().getName());
        return document;
    }

    @Override
    protected String getCoreName() {
        return coreName;
    }
}
