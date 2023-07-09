package cz.honestcity.model.subject;

import lombok.Getter;

@Getter
public enum HonestyStatus {
    HONEST(null),
    BE_CAUTION(HONEST),
    DISHONEST(BE_CAUTION),
    UNKNOWN(null);

    HonestyStatus(HonestyStatus nextLevelOfHonesty) {
        this.nextLevelOfHonesty = nextLevelOfHonesty;
    }

    private HonestyStatus nextLevelOfHonesty;


}
