package br.com.andreluisgomes.controller;

import br.com.andreluisgomes.repository.JobRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private EmbeddedWebApplicationContext webApplicationContext;

	@MockBean
	private JobRepository repository;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		/**
		 * Mock do Repositório
		 * given(repository.findByCustomQuery("Java", pageable)).willReturn(Mockito.any(Page.class));
		 */
	}


	/**
	 * q=Java&page=0&size=10&sort=salario,desc
	 *
	 * Busca por Java no titilo e descrição das Vagas indexadas.
	 *
	 * Página:0
	 *
	 * Itens por página: 10
	 *
	 * Ordenação: Salário descendente
	 */
	@Test
	public void shouldFindByTerm() throws Exception {
		mockMvc.perform(get("/search?q=Java&page=0&size=10&sort=salario,desc")).andExpect(status().isOk());
	}

	/**
	 * city=Joinville&page=0&size=10&sort=salario,asc
	 *
	 * Busca por Joinville no campo cidade.
	 *
	 * Página:0
	 *
	 * Itens por página: 10
	 *
	 * Ordenação: Salário ascendente
	 */
	@Test
	public void shouldFindByCity() throws Exception {
		mockMvc.perform(get("/search/by-city?city=Joinville&page=0&size=10&sort=salario,asc")).andExpect(status().isOk());
	}

}
