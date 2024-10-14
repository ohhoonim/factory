package dev.ohhoonim.factory.component.smtp;

public interface SmtpPort {

    void send(String email) throws StmpSendException;
    
}
