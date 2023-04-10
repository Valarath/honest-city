package cz.honestcity.service.security;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final SecurityProperties securityProperties;
    private final TextEncryptor encryptor;

    public EncryptionService(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
        this.encryptor = getEncryptor();
    }

    public String encrypt(String value){
        return encryptor.encrypt(value);
    }

    public String decrypt(String value){
        return encryptor.decrypt(value);
    }

    private TextEncryptor getEncryptor(){
        return Encryptors.text(securityProperties.getPassword(),securityProperties.getSalt());
    }
}
