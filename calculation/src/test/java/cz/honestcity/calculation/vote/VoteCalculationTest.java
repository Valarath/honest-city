package cz.honestcity.calculation.vote;

import cz.honestcity.model.vote.VoteCalculationMetric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VoteCalculationTest {

    private VoteCalculationService voteCalculationService;

    @BeforeEach
    public void init(){
        this.voteCalculationService = new VoteCalculationService(this::getTestCalculationMetric);
    }

    private VoteCalculationMetric getTestCalculationMetric(){
        return new VoteCalculationMetric()
                .setLowestValueForAcceptance(5)
                .setScoreModificator(0.5);
    }

    @Test
    public void suggestionIsAcceptable(){
        boolean acceptableResult = voteCalculationService.isAcceptable(1, 6);
        Assertions.assertTrue(acceptableResult);
    }

    @Test
    public void suggestionIsNotAcceptable(){
        boolean notAcceptableResult = voteCalculationService.isAcceptable(1, 2);
        Assertions.assertFalse(notAcceptableResult);
    }

}
