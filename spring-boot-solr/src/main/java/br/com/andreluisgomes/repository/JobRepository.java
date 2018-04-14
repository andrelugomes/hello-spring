package br.com.andreluisgomes.repository;

import br.com.andreluisgomes.document.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by andre on 23/01/17.
 */
@Repository
public interface JobRepository extends SolrCrudRepository<Job, String> {

    @Query("title:*?0* OR description:*?0*")
    public Page<Job> findByCustomQuery(String searchTerm, Pageable pageable);

    public Page<Job> findByCidadeContains(String cidade, Pageable pageable);
}
