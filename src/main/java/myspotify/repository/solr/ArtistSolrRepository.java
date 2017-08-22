package myspotify.repository.solr;

import myspotify.model.Artist;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistSolrRepository extends SolrRepository<Artist, Long> {

    @Value("${solr.cores.artists}")
    private String coreName;

    @Autowired
    public ArtistSolrRepository(SolrClient solrClient, CrudRepository<Artist, Long> repository) {
        super(solrClient, repository);
    }

    @Override
    protected String getCoreName() {
        return coreName;
    }
}
