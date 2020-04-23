package by.bsuir.gallery.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Password")
public class Password implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPassword")
    private int idPassword;

    @Column(name = "idUser")
    private int idUser;

    @Column(name = "passwordHash", nullable = false, length = 300)
    private String passwordHash;

    @OneToOne(optional = false, mappedBy = "password")
    private User user;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
