package cz.honestcity.database.rate;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface RatePostgressMapper {
    Object getRate(LocalDate day);
}
