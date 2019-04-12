package cz.honestcity.endpoints.vote;

import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private Map<String,VoteService> voteServices;

    @PostMapping("/up-vote")
    public void upVote(PostUpVoteRequest request){
        request.getVotes().forEach(vote -> voteServices.get(vote.getVoteType().name()).upVote(vote.getSuggestionId(),vote.getUserId()));
    }

}
