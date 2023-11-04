package dev.ohhoonim.factory.domain.auth.infra;

public interface SmtpPort {

    void send(String email) throws StmpSendException;
    
}
