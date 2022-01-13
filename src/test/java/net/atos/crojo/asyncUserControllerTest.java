package net.atos.crojo;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.atos.crojo.controller.UserController;
import net.atos.crojo.model.User;
import net.atos.crojo.service.UserServiceImpl;

@WebMvcTest(UserController.class)
@ComponentScan(basePackages = "net.atos.crojo")
public class asyncUserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl usrSrv;
	
	User mockUser  = new User("Carlos", "Rojo", "Cano", "charly@hotmail.com", "admin", "charlyatos","123", "0", "1", "1");
	User mockUser2  = new User("Wendy", "munoz", "Pat", "wendy@hotmail.com", "admin2", "wendyhome","123", "0", "1", "1");
	
	
	@Test
	@Description("ASYNC one get User ")
	public void getUserTest() throws Exception {
		//given
		given(usrSrv.findById(Mockito.anyLong()))
		.willReturn(
			CompletableFuture.completedFuture(mockUser)
				);
		//when
		
		ObjectMapper mapr = new ObjectMapper();
		
		String body = mapr.writeValueAsString(mockUser);
		
		String expected = "";
		MvcResult response = 
			this.mockMvc.perform(get("http://localhost:8181/api/users/16").accept(MediaType.APPLICATION_JSON) )
			.andExpect(request().asyncStarted())
			.andExpect(status().is2xxSuccessful())
			.andReturn();
		
		this.mockMvc.perform(asyncDispatch(response))
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(body))
        ;
			
			
		//then
		
	}
	
	@Test
	@Description("ASYNC save User ")
	public void saveUserTest() throws Exception {
		//given
		given(usrSrv.save(Mockito.any()))
		.willReturn(
			CompletableFuture.completedFuture(mockUser)
				);
		//when
		
		ObjectMapper mapr = new ObjectMapper();
		
		String body = mapr.writeValueAsString(mockUser);
		
		String expected = "";
		MvcResult response = 
			this.mockMvc.perform(
					post("http://localhost:8181/api/users/")
					.accept(MediaType.APPLICATION_JSON)
	                .contentType(MediaType.APPLICATION_JSON)
					.content(body)
					)
			
			.andExpect(request().asyncStarted())
			.andExpect(status().is2xxSuccessful())
			.andReturn();
		
		this.mockMvc.perform(asyncDispatch(response))
		.andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().json(body,false ))
        ;
			
			
		//then
		
	}
	
}
