package edu.uwpce.bzbookstore.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
    "file:src/main/webapp/resources/data/books.xml",
    "file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BZBooksControllerTests {
    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
    @Test
    public void testBooks() throws Exception {
        mockMvc.perform(get("/bzbooks"))
               .andExpect(status().isOk())
               .andExpect(model().attributeExists("booksMap"))
               .andExpect(model().attribute("booksMap", hasSize(4)))
               .andExpect(model().attribute("booksMap", hasItem(
                       allOf(
                               hasProperty("title", is("Neuromancer")),
                               hasProperty("author", is("William Gibson")),
                               hasProperty("genre", is("scifi")),
                               hasProperty("price", is(8.99)),
                               hasProperty("ISBN", is("978-0441569595"))
                               ))))
               .andExpect(model().attribute("booksMap", hasItem(
                       allOf(
                               hasProperty("title", is("Foundation")),
                               hasProperty("author", is("Isaac Asimov")),
                               hasProperty("genre", is("scifi")),
                               hasProperty("price", is(14.50)),
                               hasProperty("ISBN", is("978-0553293357"))
                               ))))
               .andExpect(model().attribute("booksMap", hasItem(
                       allOf(
                               hasProperty("title", is("Industrial Light and Magic - The Art of Innovation")),
                               hasProperty("author", is("Pamela Glintenkamp")),
                               hasProperty("genre", is("non-fiction")),
                               hasProperty("price", is(57.50)),
                               hasProperty("ISBN", is("978-0810998025"))
                               ))))
              .andExpect(view().name("bzbooks"));
    }
}
