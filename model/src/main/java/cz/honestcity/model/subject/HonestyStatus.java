package cz.honestcity.model.subject;

import lombok.Getter;

@Getter
public enum HonestyStatus {
    HONEST(null),
    HONEST_WITH_RESERVE(HONEST),
    BE_CAUTION(HONEST_WITH_RESERVE),
    DISHONEST(BE_CAUTION),;

    HonestyStatus(HonestyStatus nextLevelOfHonesty) {
        this.nextLevelOfHonesty = nextLevelOfHonesty;
    }

    private HonestyStatus nextLevelOfHonesty;


}
