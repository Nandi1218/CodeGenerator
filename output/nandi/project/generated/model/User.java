package nandi.project.generated.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.*;
import nandi.project.generated.model.*;

@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    @Size(min = 2, max = 255)
    private String name;
    @Email
    @Column(unique = true)
    private String emailAddress;
    @Size(min = 2, max = 255)
    private String password;
    @Column(nullable = false)
    private List<User> friends;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFriends() {
        return friends;
    }

}