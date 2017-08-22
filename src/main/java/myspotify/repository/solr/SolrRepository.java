package myspotify.repository.solr;

import myspotify.model.NamedEntity;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import static java.util.stream.Collectors.toList;

abstract public class SolrRepository<Entity extends NamedEntity, Id extends Serializable> {

    private final SolrClient solrClient;
    private final CrudRepository<Entity, Id> repository;

    protected SolrRepository(SolrClient solrClient, CrudRepository<Entity, Id> repository) {
        this.solrClient = solrClient;
        this.repository = repository;
    }

    public void save(Entity entity) throws IOException, SolrServerException {
        SolrInputDocument document = getFilledDocument(entity);
        solrClient.add(getCoreName(), document);
        solrClient.commit();
    }

    protected SolrInputDocument getFilledDocument(Entity entity) {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", entity.getId());
        document.addField("name", entity.getName());
        return document;
    }

    public List<Entity> search(String queryStr) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryStr + "*");
        QueryResponse response = solrClient.query(getCoreName(), query);

        return response.getResults()
                .stream()
                .map(doc -> repository.findOne((Id) doc.get("id")))
                .collect(toList());
    }

    public List<String> suggest(String queryStr) throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setRequestHandler("/suggest");
        query.setParam("suggest.q", queryStr);
        QueryResponse response = solrClient.query(getCoreName(), query);

        return response.getSuggesterResponse()
                .getSuggestedTerms().get("suggester");
    }

    abstract protected String getCoreName();
}
