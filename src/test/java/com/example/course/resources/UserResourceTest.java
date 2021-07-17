package com.example.course.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.UserService;

class UserResourceTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Test
    public void countElements() {
		User u1 = new User(1L, "Luis", 2123432343, "Aveiro", 923746352);
		User u2 = new User(2L, "Joana", 785856858, "Porto", 923746352);
		
		List<User> users = new ArrayList<>();
        users.add(u1); users.add(u2);
 
        assertEquals(2, users.size());
    }
	
	@Test
    public void verifyFirstName() {
		User u1 = new User(1L, "Luis", 2123432343, "Aveiro", 923746352);
		User u2 = new User(2L, "Joana", 785856858, "Porto", 923746352);
		
		List<User> users = new ArrayList<>();
        users.add(u1); users.add(u2);
         
        assertThat(users.get(0).getNome()).isEqualTo(u1.getNome());
        assertThat(users.get(1).getNome()).isEqualTo(u2.getNome());
    }
	
	@Test
    public void SaveUser() {
		User u1 = new User(1L, "Luis", 2123432343, "Aveiro", 923746352);
		userService.insert(u1);
		verify(userRepository, times(1)).save(u1);
    }

}
