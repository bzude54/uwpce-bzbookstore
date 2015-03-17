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
    public void testValidLogin() throws Exception{
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
   public void testUserRegister() throws Exception {
       mockMvc.perform(post("/bzregister").param("userName", "freddie")
               		.param("password", "bedrock1")
               		.param("firstName", "Fred")
               		.param("lastName", "Flintstone")
               		.param("phoneNumber1", "206-222-3456")
               		.param("emailAddress", "freddie@bedrock.com"))
                    .andExpect(status().is3xxRedirection())
       				.andExpect(model().attributeExists("BZUserInfo"))
       				.andExpect(model().attribute("BZUserInfo", allOf(
						hasProperty("userName", is("freddie")),
						hasProperty("password", is("bedrock1")),
						hasProperty("firstName", is("Fred")),
						hasProperty("lastName", is("Flintstone")),
						hasProperty("phoneNumber1", is("206-222-3456")))))		
       				.andExpect(view().name("redirect:/bzlogin"));

   }
   
   
   @Test
   public void testUserRegisterWithErrors() throws Exception {
       mockMvc.perform(post("/bzregister").param("userName", "ben")
               		.param("password", "fantastic")
               		.param("firstName", "Ben")
               		.param("lastName", "Grimm")
               		.param("phoneNumber1", "444-1234")
               		.param("emailAddress", "duh.com"))
                    .andExpect(status().isOk())
       				.andExpect(model().attributeHasFieldErrors("BZUserInfo", "userName"))
       				.andExpect(model().attributeHasFieldErrors("BZUserInfo", "password"))
       				.andExpect(model().attributeHasFieldErrors("BZUserInfo", "emailAddress"))
       				.andExpect(model().attributeHasFieldErrors("BZUserInfo", "phoneNumber1"))
       				.andExpect(view().name("bzregisterForm"));
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
