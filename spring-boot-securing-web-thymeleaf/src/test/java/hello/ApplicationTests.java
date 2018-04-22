package hello;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import hello.model.User;
import hello.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.profiles.active=test"}, webEnvironment = RANDOM_PORT)
public class ApplicationTests {

    public static final String EMAIL = "integration@test.com";
    public static final String PASS = "hardpassword";
    public static final String NAME = "Integration";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Before
    public void setUp(){
        final User user = new User();
        user.setName(NAME);
        user.setLastName("Test");
        user.setEmail(EMAIL);
        user.setPassword(PASS);

        userService.saveUser(user);
    }

    @Test
    public void loginWithValidUserThenAuthenticated() throws Exception {
        final LoginBuilder login = LoginBuilder.form().email(EMAIL).password(PASS);

        mockMvc.perform(login).andExpect(authenticated().withUsername(EMAIL));
    }

    @Test
    public void loginWithInvalidUserThenUnauthenticated() throws Exception {
        final LoginBuilder login = LoginBuilder.form().email("invalidEmail").password("invalidPassword");

        mockMvc.perform(login).andExpect(unauthenticated());
    }

    @Test
    public void accessUnsecuredResourceThenOk() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void accessSecuredResourceUnauthenticatedThenRedirectsToLogin() throws Exception {
        mockMvc.perform(get("/no-authenticated"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    public void accessSecuredResourceAuthenticatedThenOk() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    public static class LoginBuilder implements RequestBuilder {

        private String email;
        private String password;
        private String loginProcessingUrl = "/login";
        private MediaType acceptMediaType = MediaType.APPLICATION_FORM_URLENCODED;

        private RequestPostProcessor postProcessor = csrf();

        public static LoginBuilder form(){
            return new LoginBuilder();
        }

        @Override
        public MockHttpServletRequest buildRequest(final ServletContext servletContext) {
            MockHttpServletRequest request = post(this.loginProcessingUrl)
                    .accept(this.acceptMediaType)
                    .param("email", this.email)
                    .param("password", this.password)
                    .buildRequest(servletContext);
            return this.postProcessor.postProcessRequest(request);
        }

        public LoginBuilder password(String password) {
            this.password = password;
            return this;
        }

        public LoginBuilder email(String email) {
            this.email = email;
            return this;
        }
    }
}
