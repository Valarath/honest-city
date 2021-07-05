package cz.honestcity.database.suggestion;

import cz.honestcity.model.suggestion.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SuggestionPostgresMapper {

    @Insert("INSERT INTO suggestion(suggestion_id,user_id, status, votes)\n" +
            "values (#{suggestion.id},#{suggestion.suggestedBy.id},#{suggestion.state},0);")
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
            "SET status = #{suggestion.state}, \n" +
            " WHERE suggestion_id = #{suggestion.id}"})
    void update(@Param("suggestion")Suggestion suggestion);

}
