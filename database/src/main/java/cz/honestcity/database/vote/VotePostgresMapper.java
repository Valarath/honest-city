package cz.honestcity.database.vote;

import cz.honestcity.model.vote.Vote;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
interface VotePostgresMapper {

    @Select("SELECT votes\n" +
            "FROM suggestion\n" +
            "where suggestion_id = #{suggestionId}")
    Integer getNumberOfVotes(@Param("suggestionId") String suggestionId);

    @Insert("INSERT INTO user_votes(user_id, suggestion_id)\n" +
            "values (#{userId},#{suggestionId});")
    void recordVote(@Param("suggestionId") String suggestionId,@Param("userId") String userId);

    @Select("SELECT user_id, suggestion_id FROM user_votes \n" +
            "where suggestion_id = #{suggestionId} " +
            "AND user_id = #{userId}")
    @Results(value = {
            @Result(property = "suggestion.id", column = "suggestion_id"),
            @Result(property = "suggestion.suggestedBy.id", column = "user_id"),
    })
    List<Object> getVotes(@Param("suggestionId")String suggestionId, @Param("userId") String userId);
}
