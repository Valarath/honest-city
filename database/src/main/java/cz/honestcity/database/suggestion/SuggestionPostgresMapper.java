package cz.honestcity.database.suggestion;

import cz.honestcity.model.suggestion.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SuggestionPostgresMapper {

    @Insert("INSERT INTO suggestion(suggestion_id,user_id, status, votes, created_at)\n" +
            "values (#{suggestion.id},#{suggestion.suggestedBy.id},#{suggestion.state},0,#{suggestion.created_at})\n" +
            "ON CONFLICT (suggestion_id) DO NOTHING;")
    void suggest(@Param("suggestion") Suggestion suggestion);

    @Delete("<script>\n" +
            "DELETE\n" +
            "FROM suggestion\n" +
            "WHERE suggestion_id in\n" +
            "<foreach collection='toRemove' item='suggestion' index='index' open='(' separator =',' close=')'> \n" +
            "#{suggestion.id}\n" +
            "</foreach>" +
            "</script>")
    void removeSuggestions(@Param("toRemove") List<? extends Suggestion> toRemove);

    @Update({"UPDATE suggestion \n" +
            "SET votes = #{suggestion.votes}, \n" +
            " status = #{suggestion.state} \n" +
            " WHERE suggestion_id = #{suggestion.id}"})
    void update(@Param("suggestion")Suggestion suggestion);


    @Select("SELECT " +
            "suggestion_id, " +
            "status, " +
            "votes, " +
            "created_at " +
            "FROM suggestion WHERE suggestion_id = #{suggestionId}")
    @Results(value = {
            @Result(property = "id",column = "suggestion_id"),
            @Result(property = "state",column = "status"),
            @Result(property = "votes",column = "votes"),
            @Result(property = "createdAt",column = "created_at")
    })
    Suggestion get(@Param("suggestionId")String suggestionId);
}
