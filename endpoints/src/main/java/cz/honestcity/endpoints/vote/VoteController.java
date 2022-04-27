package cz.honestcity.endpoints.vote;

import cz.honestcity.endpoints.BaseController;
import cz.honestcity.service.vote.VoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VoteController extends BaseController {

    private final Map<String, VoteService> voteServices;

    public VoteController(Map<String, VoteService> voteServices) {
        this.voteServices = voteServices;
    }

    @PostMapping(VoteEndpointsUrl.UP_VOTE)
    public void upVote(@RequestBody PostUpVoteRequest request) {
        request.getVotes().forEach(vote -> voteServices.get(vote.getClass().getSimpleName()).upVote(vote.getSuggestion(), request.getUserId()));
    }

}
