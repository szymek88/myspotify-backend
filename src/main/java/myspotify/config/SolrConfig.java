package myspotify.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
class SolrConfig {

    @Value("${solr.home}")
    private String solrHome;

    @Value("${solr.cores.default}")
    private String defaultCoreName;

    @Bean
    SolrClient solrClient() {
        return new EmbeddedSolrServer(Paths.get(solrHome), defaultCoreName);
    }
}
