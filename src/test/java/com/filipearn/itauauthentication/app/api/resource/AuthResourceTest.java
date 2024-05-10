package com.filipearn.itauauthentication.app.api;

import com.filipearn.itauauthentication.ItauAuthenticationApplication;
import com.filipearn.itauauthentication.app.service.AuthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ItauAuthenticationApplication.class)
@AutoConfigureMockMvc
public class AuthResourceTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Test
    public void testShouldReturnOkWhenIsJwtIsValid(){
        String expected = "verdadeiro";
        when(authService.checkJwt(any())).thenReturn(expected);

        try{
            mockMvc.perform(post("/v1/authentications")
                    .param("jwt", "any"))
                    .andExpect(status().isOk())
                    .andExpect(AuthResourceTest::matchVerdadeiro);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testShouldReturnOkWhenIsJwtIsInvalid(){
        String expected = "falso";
        when(authService.checkJwt(any())).thenReturn(expected);

        try{
            mockMvc.perform(post("/v1/authentications")
                            .param("jwt", "any"))
                    .andExpect(status().isOk())
                    .andExpect(AuthResourceTest::matchFalso);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void matchVerdadeiro(MvcResult mvcResult) {
        ResponseEntity<String> future = (ResponseEntity<String>) mvcResult.getAsyncResult();
        if (future.getBody() != null && !future.getBody().equals("verdadeiro")) {
            throw new AssertionError("Unexpected response: " + future.getBody());
        }
    }

    private static void matchFalso(MvcResult mvcResult) {
        ResponseEntity<String>future = (ResponseEntity<String>) mvcResult.getAsyncResult();
        if (future.getBody() != null && !future.getBody().equals("falso")) {
            throw new AssertionError("Unexpected response: " + future.getBody());
        }
    }
}
