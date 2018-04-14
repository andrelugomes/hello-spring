package br.com.andreluisgomes.document;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andre on 23/01/17.
 */
@SolrDocument(solrCoreName = "docs")
public class Job implements Serializable {

    @Id
    @Field
    private String id;

    @Field
    private String title;

    @Field
    private String description;

    @Field
    private Float salario;

    @Field
    private List<String> cidade;

    @Field
    private List<String> cidadeFormated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public List<String> getCidade() {
        return cidade;
    }

    public void setCidade(List<String> cidade) {
        this.cidade = cidade;
    }

    public List<String> getCidadeFormated() {
        return cidadeFormated;
    }

    public void setCidadeFormated(List<String> cidadeFormated) {
        this.cidadeFormated = cidadeFormated;
    }
}
