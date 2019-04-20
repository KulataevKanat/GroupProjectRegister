package kg.it.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private int id;
    private String fio;
    private String email;
    private int phone;
    private  String password;
    private  String login;

    public  User(){

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int id, String fio, String email, int phone, String password, String login) {
        this.id = id;
        this.fio = fio;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
