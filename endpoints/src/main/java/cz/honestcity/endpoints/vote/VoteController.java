package cz.honestcity.endpoints.vote;

import cz.honestcity.service.vote.VoteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final Map<String, VoteService> voteServices;

    public VoteController(Map<String, VoteService> voteServices) {
        this.voteServices = voteServices;
    }

    @PostMapping("/up-vote")
    public void upVote(@RequestBody PostUpVoteRequest request) {
        request.getVotes().forEach(vote -> voteServices.get(vote.getClass()).upVote(vote.getSuggestionId(), request.getUserId()));
    }

}
