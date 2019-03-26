package cz.honestcity.endpoints.suggestion;

import lombok.Data;

@Data
public class GetUserSuggestionsRequest {
    private long userId;
}
