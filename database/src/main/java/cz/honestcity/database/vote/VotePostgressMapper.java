package cz.honestcity.database.vote;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
interface  VotePostgressMapper {

    @Select("")
    int getNumberOfVotes(@Param("suggestionId") long suggestionId,@Param("userId") long userId);

    void recordVote(long suggestionId, long userId);
}
