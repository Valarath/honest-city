package cz.honestcity.endpoints.validation;

import cz.honestcity.endpoints.configuration.authorization.JwtTokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenValidationController {

    private final JwtTokenService jwtTokenService;

    public TokenValidationController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping(ValidationEndpointsUrl.TOKEN_VALIDATION_URL)
    public PostValidateTokenResponse validate(@RequestBody PostValidateTokenRequest request){
        return new PostValidateTokenResponse()
                .setValid(jwtTokenService.isTokenValid(request.getToken()));
    }

}
