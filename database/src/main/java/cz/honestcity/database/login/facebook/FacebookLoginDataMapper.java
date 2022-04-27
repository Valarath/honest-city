package cz.honestcity.database.login.facebook;

import cz.honestcity.model.login.FacebookLoginData;
import org.apache.ibatis.annotations.*;

/**
 * @author michal.keder
 */
@Mapper
public interface FacebookLoginDataMapper {

    @Select("SELECT user_id, facebook_user_id from facebook_login_data where user_id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "facebookUserId", column = "facebook_user_id"),
    })
    FacebookLoginData get(@Param("userId") String userId);

    @Insert("INSERT into facebook_login_data(user_id,facebook_user_id) \n" +
            "values(#{facebookLoginData.userId},#{facebookLoginData.facebookUserId})")
    void insert(@Param("facebookLoginData") FacebookLoginData facebookLoginData);

    @Select("SELECT user_id from facebook_login_data WHERE facebook_user_id = #{facebookLoginData.facebookUserId}")
    String findUserId(@Param("facebookLoginData")FacebookLoginData loginData);
}
