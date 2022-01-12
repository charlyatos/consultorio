package net.atos.crojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import static org.hamcrest.Matchers.containsString;

import net.atos.crojo.controller.UserController;
import net.atos.crojo.dto.UserReqDTO;
import net.atos.crojo.model.User;
import net.atos.crojo.repo.UserRepository;
import net.atos.crojo.service.UserServiceImpl;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = UserController.class)
@WebMvcTest(UserController.class)
//@ContextConfiguration(classes=ConsultorioApplication.class)
//@AutoConfigureJsonTesters
@ComponentScan(basePackages = "net.atos.crojo")
class ConsultorioApplicationTests {
	
//	@Autowired
//	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl usrSrv;
	
	@MockBean
	private UserRepository usrRepo;
	
//	@Autowired
//	private JacksonTester<User> jsonUser;
	
	User mockUser  = new User("Carlos", "Rojo", "Cano", "charly@hotmail.com", "admin", "charlyatos","123", "0", "1", "1");
	User mockUser2  = new User("Wendy", "munoz", "Pat", "wendy@hotmail.com", "admin2", "wendyhome","123", "0", "1", "1");
	
	
	@Autowired
	public UserController usrController;
	
	@Test
	@Description("sevice user not null")
	public void userServiceTest() throws Exception {
		
		assertThat(usrSrv).isNotNull();
	}
	
	@Test
	@Description("controller user not null")
	public void userControllerTest() throws Exception {
		assertThat(usrController).isNotNull();
	}
	
	@Test
	@Description("Onse user")
	public void getUserTest() throws Exception {
	/*	Mockito.when(
				usrSrv.findById(Mockito.anyLong())
				).thenReturn(CompletableFuture.completedFuture( mockUser) );
		
		RequestBuilder reqBuilder = 
				MockMvcRequestBuilders
					.get("http://localhost:8181/api/users/16")
					.accept(MediaType.APPLICATION_JSON);
		
		MvcResult res = mockMvc.perform(reqBuilder).andReturn();
		
		System.out.println(res.getResponse());
		String expected ="{}";
		
		//JSONAssert.assertEquals( expected , res.getResponse(), false);
		 
		*/
		//given
		given(usrSrv.findById(Mockito.anyLong()))
		.willReturn(
			CompletableFuture.completedFuture(mockUser)
				);
		//when
		
		
//		String expected = "{\"name\"=\"Carlos\", \"lastName\"=Rojo\", \"surName\"=\"Cano\", \"email\"=\"charly@hotmail.com\", \"role\"=\"admin\", \"username\"=\"charlyatos\", \"password\"=123, \"status\"=0, \"userModifier\"=1, \"userCreator\"=1}";
//		String expected = "name=Carlos, lastName=Rojo, surName=Cano, email=charly@hotmail.com, role=admin, username=charlyatos, password=123, status=0, userModifier=1, userCreator=1";
		String expected = "";
//		MvcResult response = 
			this.mockMvc.perform(get("http://localhost:8181/api/users/16").accept(MediaType.APPLICATION_JSON) )
			.andDo(print())
			.andExpect(content().string(expected))
//			.andExpect(status().is4xxClientError());
			.andExpect(status().is2xxSuccessful())
			;
//			.andReturn();
		//then
		
	}
	
	@Test
	@Description("All users")
	public void allUserTest() throws Exception {
		
		List<User> all = new ArrayList<User>();
		all.add(mockUser);
		all.add(mockUser2);
		
		given(usrSrv.findAll())
		.willReturn(
			CompletableFuture.completedFuture(all)
				);
		//when
		
		
		String expected = "";
			this.mockMvc.perform(get("http://localhost:8181/api/users").accept(MediaType.APPLICATION_JSON) )
			.andDo(print())
			.andExpect(content().string(expected))
			.andExpect(status().is2xxSuccessful())
			;
		//then
		
	}

	@Test
	@Description("delete a user")
	public void delUserTest() throws Exception {
		
		
		given(usrSrv.delete(Mockito.anyLong()))
		.willReturn(
			CompletableFuture.completedFuture(true)
				);
		//when
		
		String expected = "";
			this.mockMvc.perform(delete("http://localhost:8181/api/users/16") )
			.andDo(print())
			.andExpect(content().string(expected))
			.andExpect(status().is2xxSuccessful())
			;
		//then
		
	}
	
	@Test
	@Description("add a user")
//	@Disabled
	public void addUserTest() throws Exception {
		
		
		given(usrSrv.save(Mockito.any()))
		.willReturn(
			CompletableFuture.completedFuture(new User())
				);
		//when
//		String Body = "{\"name\":\"Carlos\", \"lastName\":\"Rojo\", \"surName\":\"Cano\", \"email\":\"charly@hotmail.com\", \"role\":\"admin\", \"username\":\"charlyatos\", \"password\":\"123\", \"status\":\"0\", \"userModifier\":\"1\", \"userCreator\":\"1\"}";
		String Body = "{\"name\":\"Carlos\", \"lastName\":\"Rojo\", \"surName\":\"Cano\", \"email\":\"charly@hotmail.com\", \"role\":\"admin\", \"username\":\"charlyatos\", \"password\":\"123\", \"status\":\"0\", \"userModifier\":\"1\", \"userCreator\":\"1\"}";
		String expected = "";
			this.mockMvc.perform(
					post("http://localhost:8181/api/users/")
					.accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
					.content(Body)
					)
			.andDo(print())
			.andExpect(content().string(expected))
			.andExpect(status().is2xxSuccessful())
			;
		//then
		
	}
	@Test
	@Description("add a user object mapper")
//	@Disabled
	public void addUserTest2() throws Exception {
		
		
		given(usrSrv.save(Mockito.any()))
		.willReturn(
			CompletableFuture.completedFuture(new User())
				);
		//when
		ObjectMapper mapr = new ObjectMapper();
		
		String Body = mapr.writeValueAsString(mockUser); 
		String expected = "";
			this.mockMvc.perform(
					post("http://localhost:8181/api/users/")
					.accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
					.content(Body)
					)
			.andDo(print())
			.andExpect(content().string(expected))
			.andExpect(status().is2xxSuccessful())
			;
		//then
			
		
	}
}
