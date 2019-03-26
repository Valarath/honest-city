package cz.honestcity.endpoints.vote;

import cz.honestcity.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/vote-for")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/new-exchange-point")
    public void upVoteNewExchangePointSuggestion(PostUpVoteSuggestionRequest request){
        voteService.upVoteNewExchangePointSuggestion(request.getSuggestionId(),0);
    }

    @PostMapping("/exchange-point-rate-change")
    public void upVoteExchangePointRateChangeSuggestion(PostUpVoteSuggestionRequest request){
        voteService.upVoteExchangePointRateChangeSuggestion(request.getSuggestionId(),0);
    }
}
