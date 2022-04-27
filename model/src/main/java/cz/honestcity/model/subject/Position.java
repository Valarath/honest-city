package cz.honestcity.model.subject;

import cz.honestcity.model.HonestCitySerializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Position extends HonestCitySerializable {
	private double longitude;
	private double latitude;
}
