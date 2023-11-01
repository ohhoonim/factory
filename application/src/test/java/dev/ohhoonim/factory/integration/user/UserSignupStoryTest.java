package dev.ohhoonim.factory.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSignupStoryTest {
    
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void userSignupBasicStory() {
        // 입력 필수값 체크

        // 회원가입 요청
    }

    @Test
    public void userSignupResendMailStory() {
        // 이메일 발송

        
    }


    //  @Test
    // @DisplayName("회원가입 입력 필수값 체크")
    // // @WithMockUser
    // public void checkRequiredItemTest() throws Exception {
    //         mockMvc.perform(
    //                 post("/auth/checkRequiredItem")
    //                 .contentType(MediaType.APPLICATION_JSON)
    //                 .content(objectMapper.writeValueAsString(dto)))
    //                 // .with(SecurityMockMvcRequestPostProcessors.csrf())
    //             .andDo(print())
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.[?(@.message == 'success')]").exists());

    //     verify(userSignupService, times(1)).checkRequiredItem(any());
    // }

    // @Test
    // @DisplayName("회원가입 요청")
    // public void requestSignupTest() throws Exception {
    //     mockMvc.perform(
    //             post("/auth/requestSignup")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(dto)))
    //             .andDo(print())
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.[?(@.message == 'success')]").exists());

    //     verify(userSignupService, times(1)).requestSignup(any());
    // }

    // @Test
    // @DisplayName("이메일 재전송")
    // public void resendMailTest() throws Exception {
    //     mockMvc.perform(
    //             post("/auth/resendMail")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content("matthew@ohhoonim.com"))
    //             .andDo(print())
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.[?(@.message == 'success')]").exists());

    //     verify(userSignupService, times(1)).resendMail(any());
    // }
    
}
