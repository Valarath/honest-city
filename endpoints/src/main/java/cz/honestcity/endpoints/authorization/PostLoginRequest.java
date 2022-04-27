package cz.honestcity.endpoints.authorization;

import cz.honestcity.model.user.User;
import lombok.Data;

/**
 * @author michal.keder
 */
@Data
public class PostLoginRequest {

    private User user;

}
