package cz.honestcity.database.login.facebook;

import cz.honestcity.model.login.FacebookLoginData;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.login.LoginDataGateway;

/**
 * @author michal.keder
 */
@HonestCityService(beanId = FacebookLoginData.class, beanIdSpecification = LoginDataGateway.class)
public class FacebookLoginDataPostgresGateway implements LoginDataGateway<FacebookLoginData> {

    private final FacebookLoginDataMapper facebookLoginDataMapper;

    public FacebookLoginDataPostgresGateway(FacebookLoginDataMapper facebookLoginDataMapper) {
        this.facebookLoginDataMapper = facebookLoginDataMapper;
    }

    @Override
    public FacebookLoginData get(String userId) {
        return facebookLoginDataMapper.get(userId);
    }

    @Override
    public void save(FacebookLoginData loginData) {
        facebookLoginDataMapper.insert(loginData);
    }

    @Override
    public String findUserId(FacebookLoginData loginData) {
        return facebookLoginDataMapper.findUserId(loginData);
    }
}
