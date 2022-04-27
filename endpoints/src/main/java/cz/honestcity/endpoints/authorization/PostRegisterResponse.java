package cz.honestcity.endpoints.authorization;

import cz.honestcity.model.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author michal.keder
 */
@Data
@Accessors(chain = true)
public class PostRegisterResponse {

    private String accessToken;
    private User user;

}
