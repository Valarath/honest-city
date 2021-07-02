package cz.honestcity.model.login;

/**
 * @author michal.keder
 */
public abstract class LoginData {

    private String userId;
    private String className;

    public String getUserId() {
        return userId;
    }

    public LoginData setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getClassName() {
        return getClass().getSimpleName();
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
