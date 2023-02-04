package cz.honestcity.database.user;

import cz.honestcity.model.user.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserPostgresMapper {

    String TO_USER = "toUser";

    @Select("SELECT\n" +
            "  user_id,\n" +
            "  score,\n" +
            "  username,\n" +
            "  email\n" +
            "FROM \"user\"\n" +
            "WHERE user_id = #{userId}")
    @Results(id = TO_USER, value = {
            @Result(property = "id", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "score", column = "score"),
            @Result(property = "email", column = "email")
    })
    User getUser(@Param("userId") String userId);

    @Update("UPDATE \"user\"\n" +
            "SET score = #{user.score}\n" +
            "WHERE user_id = #{user.id}")
    void setUserScore(@Param("user") User user);

    @Select("SELECT score\n" +
            "FROM \"user\"\n" +
            "WHERE user_id = #{userId};")
    int getUserScore(@Param("userId") String userId);

    @Insert("INSERT INTO \"user\" (user_id, username, score, email) VALUES (#{user.id}, #{user.username}, #{user.score}, #{user.email}) \n" +
            "ON CONFLICT (user_id) DO NOTHING;")
    void saveNewUser(@Param("user") User user);

    @Update("UPDATE \"user\" \n" +
            "SET username = #{user.username}, \n" +
            "email = #{user.email} \n" +
            "WHERE user_id = #{user.id};")
    void updateUserData(@Param("user") User user);

    @Select("SELECT\n" +
            "  user_id,\n" +
            "  score,\n" +
            "  username\n" +
            "  email\n" +
            "FROM \"user\"\n" +
            "WHERE username = #{username};")
    @ResultMap(TO_USER)
    User getUserByUsername(@Param("username") String username);
}
