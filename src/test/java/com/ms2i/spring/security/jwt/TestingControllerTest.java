package com.ms2i.spring.security.jwt;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ms2i.spring.security.jwt.controllers.payload.request.LoginRequest;
import com.ms2i.spring.security.jwt.controllers.payload.response.JwtResponse;
import com.ms2i.spring.security.jwt.security.jwt.AuthTokenFilter;

/**
 * 
 * The Http controllers are tested
 * 
 * @author NOEL MAURICE
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TestingControllerTest extends ParentTest {

	private static String accessToken;
	
	/**
	 * 
	 * The public content access method is tested
	 * 
	 * @throws Exception Erreur exception
	 * 
	 */
	@Test
	public void PublicContentControllerTest() throws Exception {
		this.mockMvc
			.perform(get("/content/public"))
			.andDo(print())
			.andExpect(status().isOk());
	}
	
	/**
	 * 
	 * The signup method is tested
	 * 
	 * @throws Exception Erreur exception
	 * 
	 */
	@Test
	public void SignupControllerTest() throws Exception {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("admin");
		loginRequest.setPassword("admin");
		
		MvcResult mvcResult = this.mockMvc
			.perform(MockMvcRequestBuilders.post("/auth/signin")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(loginRequest)))
			.andDo(print())
			.andExpect(status().isOk())
			.andReturn();
		
		JwtResponse jwtResponse = this.mvcResultToObject(mvcResult, JwtResponse.class);
		
		TestingControllerTest.accessToken = jwtResponse.getAccessToken();
	}
	
	/**
	 * 
	 * The admin content access method is tested
	 * 
	 * @throws Exception Erreur exception
	 * 
	 */
	@Test
	public void AdminContentControllerTest() throws Exception {
			
		this.mockMvc
			.perform(MockMvcRequestBuilders.get("/content/admin")
				.header(AuthTokenFilter.TOKEN_HEADER_NAME, AuthTokenFilter.TOKEN_PREFIX + accessToken))
			.andDo(print())
			.andExpect(status().isOk());
	}
}

