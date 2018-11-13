package cz.honestcity.database.rate;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface RateMapper {
    Object getRate(LocalDate day);
}
