package cz.honestcity.endpoints.validation;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostValidateTokenRequest {

    private String token;
}
