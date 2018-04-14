package br.com.andreluisgomes.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

import javax.annotation.Resource;

/**
 * Created by andre on 23/01/17.
 */
@Configuration
@EnableSolrRepositories(basePackages = "br.com.andreluisgomes.repository")
@PropertySource("classpath:application.properties")
public class SolrConfig {

    @Resource
    private Environment environment;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient(environment.getRequiredProperty("solr.server.url"));
    }

    @Bean
    public SolrOperations solrTemplate() {
        SolrTemplate solrTemplate = new SolrTemplate(solrClient());
        solrTemplate.setSolrCore("docs");
        return solrTemplate;
    }
}
