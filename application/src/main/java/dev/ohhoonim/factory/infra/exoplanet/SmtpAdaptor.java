package dev.ohhoonim.factory.infra.exoplanet;

import org.springframework.stereotype.Component;

import dev.ohhoonim.factory.domain.auth.infra.SmtpPort;
import dev.ohhoonim.factory.domain.auth.infra.StmpSendException;

@Component
public class SmtpAdaptor implements SmtpPort {

    @Override
    public void send(String email) throws StmpSendException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }
    
}
