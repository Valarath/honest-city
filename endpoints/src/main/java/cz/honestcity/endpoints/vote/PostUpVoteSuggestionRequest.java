package cz.honestcity.endpoints.vote;

import cz.honestcity.model.user.User;
import lombok.Data;

@Data
public class PostUpVoteSuggestionRequest {
    private long suggestionId;
    private long userId;
}
