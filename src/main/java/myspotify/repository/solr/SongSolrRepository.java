package myspotify.repository.solr;

import myspotify.model.Song;
import myspotify.repository.SongRepository;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.*;

@Repository
public class SongSolrRepository {

    private static final String QUERY_PARAMETER = "suggest.q";
    private static final String SONGS_CORE_NAME = "songs";

    private final SolrClient solrClient;
    private final SongRepository songRepository;

    @Value("${solr.suggester.requestHandler}")
    private String suggesterRequestHandlerName;

    @Value("${solr.suggester.dictionary}")
    private String suggesterDictionaryName;

    @Autowired
    public SongSolrRepository(SolrClient solrClient, SongRepository songRepository) {
        this.solrClient = solrClient;
        this.songRepository = songRepository;
    }

    public void indexSong(Song song) throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", song.getId());
        document.addField("name", song.getName());
        document.addField("artistName", song.getArtist().getName());
        solrClient.add(document);
        solrClient.commit();
    }

    public List<Song> searchForSong(String queryStr) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryStr + "*");
        QueryResponse response = solrClient.query(SONGS_CORE_NAME, query);

        return response.getResults()
                .stream()
                .map(doc -> songRepository.findOne((Long) doc.get("id")))
                .collect(toList());
    }

    public List<String> suggestSongs(String queryStr) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setRequestHandler(suggesterRequestHandlerName);
        query.setParam(QUERY_PARAMETER, queryStr);
        QueryResponse response = solrClient.query(SONGS_CORE_NAME, query);
        return response.getSuggesterResponse()
                .getSuggestedTerms().get(suggesterDictionaryName);
    }

}
