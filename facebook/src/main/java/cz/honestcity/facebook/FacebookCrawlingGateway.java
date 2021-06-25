package cz.honestcity.facebook;

import cz.honestcity.model.login.FacebookLoginData;
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
        return new User()
                .setId(idProvider.provideNewId())
                .setEmail(userProfile.getEmail())
                .setUsername(userProfile.getName());
    }

    private org.springframework.social.facebook.api.User getUserProfile(FacebookTemplate facebookTemplate) {
        return facebookTemplate.fetchObject("me", org.springframework.social.facebook.api.User.class,FETCHED_DATA);
    }


}
