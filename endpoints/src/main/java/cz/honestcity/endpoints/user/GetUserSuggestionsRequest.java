package cz.honestcity.endpoints.user;

import lombok.Data;

@Data
public class GetUserSuggestionsRequest {
    private long userId;
}
