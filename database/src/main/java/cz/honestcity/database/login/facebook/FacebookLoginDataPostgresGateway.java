package cz.honestcity.database.login.facebook;

import cz.honestcity.model.login.FacebookLoginData;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.login.LoginDataGateway;
import cz.honestcity.service.security.EncryptionService;

/**
 * @author michal.keder
 */
@HonestCityService(beanId = FacebookLoginData.class, beanIdSpecification = LoginDataGateway.class)
public class FacebookLoginDataPostgresGateway implements LoginDataGateway<FacebookLoginData> {

    private final FacebookLoginDataMapper facebookLoginDataMapper;
    private final EncryptionService encryptionService;

    public FacebookLoginDataPostgresGateway(FacebookLoginDataMapper facebookLoginDataMapper, EncryptionService encryptionService) {
        this.facebookLoginDataMapper = facebookLoginDataMapper;
        this.encryptionService = encryptionService;
    }

    @Override
    public FacebookLoginData get(String userId) {
        FacebookLoginData facebookLoginData = facebookLoginDataMapper.get(userId);
        decrypt(facebookLoginData);
        return facebookLoginData;
    }

    @Override
    public void save(FacebookLoginData loginData) {
        encrypt(loginData);
        facebookLoginDataMapper.insert(loginData);
    }

    @Override
    public String findUserId(FacebookLoginData loginData) {
        encrypt(loginData);
        String userId = facebookLoginDataMapper.findUserId(loginData);
        decrypt(loginData);
        return userId;
    }

    private void encrypt(FacebookLoginData loginData){
        loginData.setFacebookUserId(encryptionService.encrypt(loginData.getFacebookUserId()));
    }

    private void decrypt(FacebookLoginData loginData){
        loginData.setFacebookUserId(encryptionService.decrypt(loginData.getFacebookUserId()));
    }
}
