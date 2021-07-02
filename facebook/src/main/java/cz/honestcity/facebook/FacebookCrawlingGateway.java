package cz.honestcity.facebook;

import cz.honestcity.model.login.FacebookLoginData;
import cz.honestcity.model.login.LoginData;
import cz.honestcity.model.user.User;
import cz.honestcity.service.configuration.HonestCityService;
import cz.honestcity.service.configuration.IdProvider;
import cz.honestcity.service.login.LoginGateway;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

/**
 * @author michal.keder
 */
@HonestCityService(beanId = FacebookLoginData.class)
public class FacebookCrawlingGateway implements LoginGateway<FacebookLoginData> {

    private static final String[] FETCHED_DATA = {"email","name"};

    private final IdProvider idProvider;

    public FacebookCrawlingGateway(IdProvider idProvider) {
        this.idProvider = idProvider;
    }

    @Override
    public User getUser(FacebookLoginData loginData){
        FacebookTemplate facebookTemplate = new FacebookTemplate(loginData.getAccessToken());
        org.springframework.social.facebook.api.User userProfile = getUserProfile(facebookTemplate);
        String userId = idProvider.provideNewId();
        return getNewUser(loginData, userProfile, userId);
    }

    private User getNewUser(FacebookLoginData loginData, org.springframework.social.facebook.api.User userProfile, String userId) {
        return new User()
                .setId(userId)
                .setEmail(userProfile.getEmail())
                .setUsername(userProfile.getName())
                .setLoginData(updateLoginData(loginData, userId));
    }

    private LoginData updateLoginData(FacebookLoginData loginData, String userId){
        return loginData.setUserId(userId);
    }

    private org.springframework.social.facebook.api.User getUserProfile(FacebookTemplate facebookTemplate) {
        return facebookTemplate.fetchObject("me", org.springframework.social.facebook.api.User.class,FETCHED_DATA);
    }


}
