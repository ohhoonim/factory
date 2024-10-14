package dev.ohhoonim.factory.component.auths.service;

import org.springframework.stereotype.Component;

import dev.ohhoonim.factory.component.smtp.SmtpPort;
import dev.ohhoonim.factory.component.smtp.StmpSendException;

@Component
public class SmtpAdaptor implements SmtpPort {

    @Override
    public void send(String email) throws StmpSendException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }
    
}
