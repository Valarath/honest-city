package cz.honestcity.model;

/**
 * @author michal.keder
 */
public class HonestCitySerializable{

    private String className;

    public String getClassName() {
        return getClass().getSimpleName();
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
