package dev.ohhoonim.factory.domain.auth;

public interface UserSignupUsecase {

    /**
     * 회원가입 링크 선택 
     * 
     * <ol>
     * <li>사용자 정보 입력됨</li>
     * </ol>
     * 
     * @param command
     */
    void checkRequiredItem(UserSignupCommand command);

    // 
    /**
     * 회원 생성 요청
     * 
     * <ol>
     * <li>회원정보 유효서 확인됨</li>
     * <li>회원 계정 생성됨</li>
     * <li>인증 메일 발송됨-메일발송 실패에 대한 예외처리는 하지 않는다.</li>
     * </ol>
     * 
     * @param command
     */
    void requestSignup(UserSignupCommand command);

    /**
     * 인증메일 재전송
     */
    void resendMail(String email);

}
