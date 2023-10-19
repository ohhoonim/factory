package dev.ohhoonim.factory.api.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import dev.ohhoonim.factory.api.config.JwtService;
import dev.ohhoonim.factory.domain.auth.api.UserSignupUsecase;
import dev.ohhoonim.factory.infra.personal.auth.repository.TokenRepository;

@WebMvcTest(controllers = UserSignupRest.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserSignupRestTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserSignupUsecase userSignupUsecase;

    @MockBean
    JwtService jwtService;

    @MockBean
    TokenRepository tokenRepository;

    @Test
    @DisplayName("회원가입 입력 필수값 체크")
    // @WithMockUser
    public void checkRequiredItemTest() throws Exception {
        mockMvc.perform(post("/auth/checkRequiredItem")
            .contentType(MediaType.APPLICATION_JSON)
            // .with(SecurityMockMvcRequestPostProcessors.csrf())        
        ).andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 요청")
    public void requestSignupTest() {

    }

    @Test
    @DisplayName("이메일 재전송")
    public void resendMailTest() {

    }
}
