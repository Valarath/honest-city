package cz.honestcity.model.login;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author michal.keder
 */
@Data
@Accessors(chain = true)
public class FacebookLoginData extends LoginData{
    private String facebookUserId;
    private String accessToken;
}
