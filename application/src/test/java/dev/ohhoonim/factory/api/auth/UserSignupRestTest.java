package dev.ohhoonim.factory.api.auth;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ohhoonim.factory.api.auth.dto.UserSignupRequest;
import dev.ohhoonim.factory.api.config.JwtService;
import dev.ohhoonim.factory.domain.auth.api.UserSignupUsecase;
import dev.ohhoonim.factory.infra.personal.auth.repository.TokenRepository;

@WebMvcTest(controllers = UserSignupRest.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserSignupRestTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserSignupUsecase userSignupService;

    @MockBean
    JwtService jwtService;

    @MockBean
    TokenRepository tokenRepository;

    private UserSignupRequest dto;

    @BeforeEach
    void initDto() {
        dto = new UserSignupRequest(
                "matthew@ohhoonim.com",
                "1234Qwert",
                "1234Qwert",
                "matthew");
    }

    @Test
    @DisplayName("회원가입 입력 필수값 체크")
    // @WithMockUser
    public void checkRequiredItemTest() throws Exception {
            mockMvc.perform(
                    post("/auth/checkRequiredItem")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                    // .with(SecurityMockMvcRequestPostProcessors.csrf())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.message == 'success')]").exists());

        verify(userSignupService, times(1)).checkRequiredItem(any());
    }

    @Test
    @DisplayName("회원가입 요청")
    public void requestSignupTest() throws Exception {
        mockMvc.perform(
                post("/auth/requestSignup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.message == 'success')]").exists());

        verify(userSignupService, times(1)).requestSignup(any());
    }

    @Test
    @DisplayName("이메일 재전송")
    public void resendMailTest() throws Exception {
        mockMvc.perform(
                post("/auth/resendMail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("matthew@ohhoonim.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.message == 'success')]").exists());

        verify(userSignupService, times(1)).resendMail(any());
    }

    @Test
    @DisplayName("Exception 발생시")
    public void resendMailTest_fail() throws Exception {
        mockMvc.perform(
                post("/auth/resendMail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", equalTo("ERROR")))
                .andExpect(jsonPath("$.message", startsWith("Required")));

        verify(userSignupService, times(0)).resendMail(any());
    }
}
