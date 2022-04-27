package cz.honestcity.endpoints.vote;

import cz.honestcity.model.vote.Vote;
import lombok.Data;

import java.util.List;

@Data
public class PostUpVoteRequest {
    private List<? extends Vote> votes;
    private String userId;
}
