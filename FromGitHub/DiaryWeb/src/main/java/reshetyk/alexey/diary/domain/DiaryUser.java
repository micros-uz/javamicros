package reshetyk.alexey.diary.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
public class DiaryUser implements Serializable {

    @Id
    @Column(name = "ID_USER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "LOGIN", unique = true, nullable = false)
    private String login;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public DiaryUser() {
    }
    public DiaryUser(Integer id) {
        this.id = id;
    }

    public DiaryUser(String login) {
        this.login = login;
    }

    public DiaryUser(Integer id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
