package cz.honestcity.database.vote;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
interface VotePostgresMapper {

    @Select("SELECT votes\n" +
            "FROM suggestion\n" +
            "where suggestion_id = #{suggestionId}")
    Integer getNumberOfVotes(@Param("suggestionId") String suggestionId);

    @Insert("INSERT INTO user_votes(user_id, suggestion_id)\n" +
            "values (#{userId},#{suggestionId});")
    void recordVote(@Param("suggestionId") String suggestionId,@Param("userId") String userId);
}
