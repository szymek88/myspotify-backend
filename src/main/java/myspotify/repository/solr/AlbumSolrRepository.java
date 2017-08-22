package myspotify.repository.solr;

import myspotify.model.Album;
import myspotify.repository.AlbumRepository;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumSolrRepository extends SolrRepository<Album, Long> {

    @Value("${solr.cores.albums}")
    private String coreName;

    @Autowired
    public AlbumSolrRepository(SolrClient solrClient, AlbumRepository albumRepository) {
        super(solrClient, albumRepository);
    }

    protected SolrInputDocument getFilledDocument(Album album) {
        SolrInputDocument document = super.getFilledDocument(album);
        document.addField("artistName", album.getArtist().getName());
        return document;
    }

    @Override
    protected String getCoreName() {
        return coreName;
    }
}