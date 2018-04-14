# Jobs Search App

API contruida usando Spring Boot e Solr como ferramenta de search engine.

## Requisitos

- [Java JDK 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
- [Docker](https://www.docker.com/products/overview)
- [Maven](https://maven.apache.org/download.cgi)

## Por que?
Conforme foi conversado, poderia fazer o desafio em outras tecnologias alem de PHP. 
Tomei a decisão de usar Java por ser mais familiarizado.

### Frameworks
#### [Spring Boot](https://projects.spring.io/spring-boot/)
A escolha do framework foi baseada na facilidade e agilidade na construção de aplicações web.

#### [Solr](http://lucene.apache.org/solr/)

Como um dos requisitos é **desenpenho**, optei por indexar o JSON do desafio na plataforma de busca.

Acredito que em uma situação real, o Solr é uma excelente acertada para buscas de alto desenpenho. A indexação de novos item pode ser feita 
pela sua API Rest ou por clientes implementados em diversas linguagens.

Para fazer a indexação de forma rápida dado o tempo do desafio, fiz uma pequena alteração no JSON para envia-lo ao Solr com apenas um comando.

```json
[
    {
        "title": "Analista de Suporte de TI",
        "description": "<li> Prestar atendimento ...</li>",
        "salario": 3200,
        "cidade": [
            "Joinville"
        ],
        "cidadeFormated": [
            "Joinville - SC (1)"
        ]
    },
    
    //...
]
```

#### [Spring Data Solr](http://projects.spring.io/spring-data-solr/)
Helper do Spring Boot pra integração com Solr.

#### [Spring Starter Web](http://projects.spring.io/spring-boot/)
Helper do Spring Boot pra criação de aplicações Web MVC / REST.

#### [Swagger](http://swagger.io/)
Framework para documentação de APIs REST.

## Configurando o ambiente
Todas as instruções de configuração do ambiente foi baseada e contruida com [Ubuntu](https://www.ubuntu.com/download/desktop) como sistema operacional.

### Solr
Rode os seguntes comandos no terminal.

Pull da imagem oficial, container na porta 8983 e criação de schema chamado **docs**.
```bash
./solr-config.sh
```
Configuração do Schema e Indexação do JSON.
```
curl http://localhost:8983/solr/docs/schema \
    -X POST \
    -H 'Content-type:application/json' \
    --data-binary '{
      "add-field":{
         "name":"title",
         "type":"string",
         "stored":true },
      "add-field":{
         "name":"description",
         "type":"string",
         "stored":true },
      "add-field":{
         "name":"salario",
         "type":"float",
         "stored":true },
      "add-field":{
         "name":"cidade",
         "type":"string",
         "multiValued":true,
         "stored":true },
      "add-field":{
         "name":"cidadeFormated",
         "type":"string",
         "multiValued":true,
         "stored":true }
    }';

curl http://localhost:8983/solr/docs/update?commit=true \
    -X POST \
    -H 'Content-type:application/json' \
    --data-binary @vagas_to_index.json;
```

Verificar se esta rodando
```
http://localhost:8983/solr/
```
### API Java

```bash
mvn clean install spring-boot:run
```

Verificar se esta rodando
```
http://localhost:8080/swagger-ui.html
```

## Testes

Para realizar os testes podemos:

- Usar a interface web do Swagger.
- Usar diretamente os endpoits em um client HTTP.
- Rodar os testes de integração.

### Api de Busca
Busca por Texto

GET http://localhost:8080/search?q=<TERMO_DE_BUSCA>&page=0&size=10&sort=salario,desc

Busca por Cidade

GET http://localhost:8080/search/by-city?city=<NOME_DA_CIDADE>&page=1&size=10&sort=salario,desc

Ordenação e Paginação
- page :  Qual a página;
- size : Quantidade de elementos na página;
- sort : Ordenação. **salario,[desc|asc]**

### Interface web do Swagger

Busca por Texto

http://localhost:8080/swagger-ui.html#!/search/byTermUsingGET

Busca por Cidade

http://localhost:8080/swagger-ui.html#!/search/byCityUsingGET

### Response Body da Api
```json
{
  "content": [
    {
      "id": "568b24ae-aed0-493b-a016-02b1d352514a",
      "title": "Analista de Controladoria",
      "description": "<li> Elaboração de cronograma financeiro de obras, acompanhamento de cronogramas financeiros, revisão das margens de obras, orientações a tesouraria da empresa sobre as atividades de pagamentos e recebimentos.</li><li> Domínio no pacote Office. Noções de contabilidade. Conhecimentos em finanças corporativas. Desejável experiência com gestão financeira de obras de engenharia. Ensino Superior.</li>",
      "salario": 3000,
      "cidade": [
        "Joinville"
      ],
      "cidadeFormated": [
        "Joinville - SC (1)"
      ]
    },
    
    //...
  ],
  "facetResultPages": [],
  "facetQueryResult": {
    "content": [],
    "last": true,
    "totalPages": 1,
    "totalElements": 0,
    "sort": null,
    "numberOfElements": 0,
    "first": true,
    "size": 0,
    "number": 0
  },
  "highlighted": [],
  "maxScore": null,
  "fieldStatsResults": {},
  "facetPivotFields": [],
  "allFacets": [
    null
  ],
  "facetFields": [],
  "totalPages": 10,
  "totalElements": 92,
  "last": false,
  "sort": [
    {
      "direction": "DESC",
      "property": "salario",
      "ignoreCase": false,
      "nullHandling": "NATIVE",
      "ascending": false
    }
  ],
  "numberOfElements": 10,
  "first": false,
  "size": 10,
  "number": 1
}
```

### Testes de Integração

Devido ao tempo curto eu fiz dois testes usando o framework de tests do Spring. Devido a isso, não evolui requisitos de mocks das requisições no Solr.

Uma ideia seria usar um Solr Embedded apenas da fase de testes e poder manipular os dados.

Para rodas os testes de integração o Container do Solr deve estar em pé e configurado.

```bash
mvn test
```

## Coisas que não tive o tempo de fazer e Considerações

- Worker para indexar no solr lendo o JSON original diretamenre.
- Melhorar o mecanismo de busca estudando os wildcards corretos para Case Sensitive, Score de Relevância e Highlights.
- Não apliquei camada de Serviço na API. Estou injetando nos controller os repositories diretamente. 
Tomei essa decisão para não fazer camadas de proxy sem regras de negócio.
- Por não haver complexidade de  implementação de regras de negócio, não escrevi testes unitários.