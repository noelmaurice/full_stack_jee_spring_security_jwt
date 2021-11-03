package com.ms2i.spring.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * All the test classes inherit of this parent test class
 * 
 * @author NOEL MAURICE
 *
 */
class ParentTest {

	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	protected ObjectMapper objectMapper;

	
	/**
	 * 
	 * MvcResult object parsed to type object
	 * 
	 * @param <T> Type of the result object
	 * @param mvcResult MvcResultObject to parse
	 * @param responseClass Class of the object to return in accordance with the type object result defined
	 * @return The object result
	 * @throws Exception Exception returned when error found
	 */
	protected <T> T mvcResultToObject(MvcResult mvcResult, Class<T> responseClass) throws Exception
	{
		String contentAsString = mvcResult.getResponse().getContentAsString();
		
	    return objectMapper.readValue(contentAsString, responseClass);
	}
}
