package cz.honestcity.model.login;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author michal.keder
 */
@Data
@Accessors(chain = true)
public class FacebookLoginData implements LoginData{
    private String accessToken;
}
