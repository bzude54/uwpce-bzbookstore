package edu.uwpce.bzbookstore.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
	    "file:src/main/webapp/resources/data/*.xml",
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
    public void testLogin() throws Exception{
        mockMvc.perform(
            post("/bzlogin").param("userName", "buzz")
                          .param("password", "infinity"))
                          .andExpect(status().is3xxRedirection())
         				  .andExpect(model().attributeExists("BZUserInfo"))
        				  .andExpect(model().attribute("BZUserInfo", allOf(
        						  hasProperty("userName", is("buzz")),
        						  hasProperty("password", is("infinity")))))
           				  .andExpect(view().name("redirect:/bzbooks"));
    }
   
    @Test
    public void testUserAccountInfo() throws Exception {
        mockMvc.perform(get("/bzaccountinfo/100"))
                       .andExpect(status().isOk())
        				.andExpect(model().attributeExists("bZUserInfo"))
        				.andExpect(model().attribute("bZUserInfo", allOf(
 						hasProperty("userName", is("buzz")),
						hasProperty("password", is("pwd")),
						hasProperty("userId", is(100)),
						hasProperty("firstName", is("George")),
						hasProperty("lastName", is("Jetson")),
						hasProperty("phoneNumber1", is("206-574-8090")))));		

    }

}
