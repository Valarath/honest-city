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
    int getNumberOfVotes(@Param("suggestionId") long suggestionId);

    @Insert("INSERT INTO user_votes(user_id, suggestion_id)\n" +
            "values (#{userId},#{suggestionId});")
    void recordVote(@Param("suggestionId") long suggestionId,@Param("userId") long userId);
}
