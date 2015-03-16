package edu.uwpce.bzbookstore.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
    "file:src/main/webapp/resources/data/books.xml",
    "file:src/main/webapp/resources/data/users.xml",
    "file:src/main/webapp/WEB-INF/spring/*.xml",
    "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml"})
public class BZUserInfoControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testLoginShortPassword() throws Exception{
        mockMvc.perform(
            post("/bzlogin").param("name", "Buzz Lightyear")
                          .param("password", "beyond"))
          .andExpect(status().isOk())
          .andExpect(model().attributeHasFieldErrors("BZUserInfo", "password"))
          .andExpect(view().name("bzlogin"));;
    }

}
