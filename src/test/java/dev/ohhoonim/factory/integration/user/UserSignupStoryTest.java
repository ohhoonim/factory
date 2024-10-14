package dev.ohhoonim.factory.integration.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.ohhoonim.factory.component.auths.service.dto.UserSignupRequest;
import dev.ohhoonim.factory.configuration.DefaultResponseBody;
import dev.ohhoonim.factory.type.ResponseCodeEnum;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserSignupStoryTest {

        @Autowired
        TestRestTemplate restTemplate;

        @Test
        public void userSignupBasicStory() {
                UserSignupRequest dto = new UserSignupRequest(
                                "matthew@ohhoonim.com",
                                "1234Qwert",
                                "1234Qwert",
                                "matthew");
                // 입력 필수값 체크
                ResponseEntity<DefaultResponseBody> commandRequiredInputResponse = restTemplate.postForEntity(
                                "/auth/checkRequiredItem", dto, DefaultResponseBody.class);
                assertEquals(HttpStatus.OK, commandRequiredInputResponse.getStatusCode());

                DefaultResponseBody requiredInputResponseBody = commandRequiredInputResponse.getBody();
                assertEquals(ResponseCodeEnum.SUCCESS, requiredInputResponseBody.getCode());

                // 회원가입 요청
                ResponseEntity<DefaultResponseBody> commandRequestSignupReponse = restTemplate.postForEntity(
                                "/auth/requestSignup", dto, DefaultResponseBody.class);
                assertEquals(HttpStatus.OK, commandRequestSignupReponse.getStatusCode());
                DefaultResponseBody requestSignupReponseBody = commandRequestSignupReponse.getBody();
                assertEquals(ResponseCodeEnum.SUCCESS, requestSignupReponseBody.getCode());
                
                // todo 회원 삭제 또는 비활성 기능 구현 필요
        }

}
