package com.jorojala.toolshare;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToolshareApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void testHomePage() throws Exception {
		mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(content().string(containsString("Welcome to Tool Share!")))
				.andExpect(status().isOk());

	}

	@Test
	void testSignUp() throws Exception {
		mockMvc.perform(get("/signup"))
				.andDo(print())
				.andExpect(content().string(containsString("<h1>Sign Up</h1>")))
				.andExpect(status().isOk());
	}

	@Test
	void testLogin() throws Exception {
		mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(content().string(containsString("<h1>Log in</h1>")))
				.andExpect(status().isOk());
	}

	@Test
	void testAboutUs() throws Exception {
		mockMvc.perform(get("/aboutus"))
				.andDo(print())
				.andExpect(content().string(containsString("About")))
				.andExpect(status().isOk());
	}

}
