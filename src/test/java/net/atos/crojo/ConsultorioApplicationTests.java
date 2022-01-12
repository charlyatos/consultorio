package net.atos.crojo;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import static org.hamcrest.Matchers.containsString;

import net.atos.crojo.controller.UserController;
import net.atos.crojo.model.User;
import net.atos.crojo.repo.UserRepository;
import net.atos.crojo.service.UserServiceImpl;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = UserController.class)
//@ContextConfiguration(classes=ConsultorioApplication.class)
@AutoConfigureJsonTesters
class ConsultorioApplicationTests {
	
//	@Autowired
//	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl usrSrv;
	
	@MockBean
	private UserRepository usrRepo;
	
	@Autowired
	private JacksonTester<User> jsonUser;
	
	User mockUser  = new User("Carlos", "Rojo", "Cano", "charly@hotmail.com", "admin", "charlyatos","123", "0", "1", "1");
	
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
	@Description("controller not null")
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
//		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
		//given
		given(usrRepo.findById(Mockito.anyLong()))
		.willReturn(
			Optional.of(mockUser)
				);
//		
		//when
		
//		MockHttpServletRequestBuilder resb = get("/api/users/");
//        .accept(MediaType.APPLICATION_JSON);
//		this.mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
		this.mockMvc.perform(get("http://localhost:8181/"))
			.andDo(print())
			.andExpect(status().is4xxClientError());
//			.andExpect(status().is2xxSuccessful());
//			.andExpect(content().string(containsString("Hello, World")));
//		this.mockMvc.perform(resb);
//		
//		System.out.println(resb.toString());
		
		//MockHttpServletResponse 
//		MvcResult response = 
//				mockMvc.perform(
//                resb
//                ).andReturn();
//                .getResponse();
		
		//then
//		System.out.println(response.getResponse());
		
		
		
	}

}
