package cz.honestcity.database.subject;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubjectSettingsMapper {

    @Select("SELECT text from analyze_text_settings where type='exchangePointName';" )
    List<String> get();

}
