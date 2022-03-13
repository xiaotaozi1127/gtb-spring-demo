package com.example.springbootdemo.integrationTest;

import com.example.springbootdemo.Domain.Authority;
import com.example.springbootdemo.Domain.User;
import com.example.springbootdemo.application.UserService;
import com.example.springbootdemo.infrastructure.UserRepository;
import com.example.springbootdemo.userInterface.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
public class ApplicationTestsUsingMVCTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    JacksonTester<List<User>> userJson;

    @Test
    @WithMockUser(value = "admin", password = "password123", roles = {"admin"})
    public void should_get_all_users_for_admin_role() throws Exception {

        User user = User.builder().enabled(true).username("admin").age(18)
                .authorityList(List.of(Authority.builder().authority("ROLE_admin").build()))
                .build();
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        when(userService.findAll()).thenReturn(List.of(user));
        MockHttpServletResponse response = mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        //[{"id":null,"username":"admin","authorityList":[{"authority":"ROLE_admin"}],"age":18,"enabled":true}]
        String contentAsString = response.getContentAsString();

        String json = userJson.write(List.of(user)).getJson();
        Assertions.assertEquals(json, contentAsString);

    }
}
