package myspotify.service;

import myspotify.model.NamedEntity;
import myspotify.repository.solr.SolrRepository;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Service<Entity extends NamedEntity, Id extends Serializable> {

    private final JpaRepository<Entity, Id> songRepository;
    private final SolrRepository<Entity, Id> solrRepository;

    public Service(JpaRepository<Entity, Id> repository, SolrRepository<Entity, Id> solrRepository) {
        this.songRepository = repository;
        this.solrRepository = solrRepository;
    }

    @Transactional
    public Entity save(Entity entity) throws IOException, SolrServerException {
        Entity savedEntity = songRepository.save(entity);
        solrRepository.save(savedEntity);
        return savedEntity;
    }

    public Entity findOne(Id id) {
        return songRepository.findOne(id);
    }

    public List<Entity> findAll() {
        return songRepository.findAll();
    }

    public List<Entity> search(String query) throws IOException, SolrServerException {
        return solrRepository.search(query);
    }

    public List<String> suggest(String query) throws IOException, SolrServerException {
        return solrRepository.suggest(query);
    }
}
