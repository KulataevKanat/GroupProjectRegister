package kg.it.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userauth")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAuth {
    public String auth;
    public String message;

    public UserAuth() {
    }

    public UserAuth(User user) {

        auth = user.getPassword() + user.getPassword();
    }

    public void dataCheking(String login, String password) {
        int sumLogin = 0;
        int sumPassword = 0;

        for (int i = 0; i < login.length(); i++) {
            sumLogin += login.charAt(i);
        }

        for (int i = 0; i < password.length(); i++) {
            sumPassword += password.charAt(i);
        }

        auth = sumLogin + " " + sumPassword;
    }
}
