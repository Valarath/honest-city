package cz.honestcity.endpoints;

import cz.honestcity.endpoints.configuration.authorization.JwtUserDetail;
import cz.honestcity.model.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author michal.keder
 */
public abstract class BaseController {

    protected  <DTO> Map<String, ? extends List<? extends DTO>> getDtosByClass(List<? extends DTO> suggestions) {
        return suggestions.stream()
                .collect(Collectors.groupingBy(it -> it.getClass().getSimpleName()));
    }

    protected User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((JwtUserDetail) authentication.getPrincipal()).getUser();
    }

}
