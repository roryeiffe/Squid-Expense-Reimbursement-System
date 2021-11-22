package User;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private boolean isMang;

    public User() {

    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, boolean isMang) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isMang = isMang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMang() {
        return isMang;
    }

    public void setMang(boolean mang) {
        isMang = mang;
    }
}
